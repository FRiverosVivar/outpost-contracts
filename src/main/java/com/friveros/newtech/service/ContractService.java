package com.friveros.newtech.service;

import com.friveros.newtech.Contract;
import com.friveros.newtech.History;
import com.friveros.newtech.dto.Point;
import com.friveros.newtech.enums.ActionEnums;
import com.friveros.newtech.mapper.ContractMapper;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheQuery;
import io.quarkus.panache.common.Page;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
@ApplicationScoped
public class ContractService {
    @ConfigProperty(name = "pagination.default-page")
    int defaultPage;

    @ConfigProperty(name = "pagination.default-size")
    int defaultSize;

    private final ContractMapper contractMapper;
    private final HistoryService historyService;
    private final SequenceGeneratorService sequenceGeneratorService;
    private static final Logger LOGGER = Logger.getLogger(ContractService.class.getName());
    public ContractService(ContractMapper contractMapper, HistoryService historyService, SequenceGeneratorService sequenceGeneratorService) {
        this.contractMapper = contractMapper;
        this.historyService = historyService;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }
    public Uni<Contract> persistContract(Contract contract, Point point) {
        if (contract == null || point == null) {
            throw new WebApplicationException("Invalid Coords or Contract", Response.Status.BAD_REQUEST);
        }
        contract.setCreatedAt(Instant.now());
        contract.numberId = sequenceGeneratorService.getNextSequence("contractSequence");

        return Contract.persist(contract)
                .replaceWith(contract)
                .map(this.contractMapper::toSchema)
                .replaceWith(contract)
                .onItem().transformToUni(ignored -> {
                    List<ActionEnums> listOfActions = new ArrayList<ActionEnums>();
                    listOfActions.add(ActionEnums.CREATED);
                    History history = historyService.createHistoryFromContract(contract.id.toString(), listOfActions, point);
                    return historyService.persistHistory(history)
                            .onItem().transform(ignoredHistory -> contract);
                })
                .onFailure(Is404Exception.IS_404).recoverWithNull();
    }
    public Uni<Contract> removeContractById(String id) {
        return this.findContractById(id)
                .onItem().transformToUni(contract -> Contract.<Contract>deleteById(new ObjectId(id)).replaceWith(contract)
                        .onFailure(Is404Exception.IS_404).recoverWithNull());
    }
    public Uni<Contract> updateContract(Contract contract, Point point) {
        return Contract.<Contract>findById(contract.id)
                .onItem().transformToUni(existingContract -> {
                    this.contractMapper.merge(contract, existingContract);
                    return existingContract.update().replaceWith(existingContract)
                            .onItem().transformToUni(ignored -> {
                                History history = historyService.createHistoryFromContract(contract.id.toString(),
                                        this.historyService.getActionsFromContract(contract)
                                        , point);
                                return historyService.persistHistory(history)
                                        .onItem().transform(ignoredHistory -> contract);
                            });
                }).onFailure(Is404Exception.IS_404).recoverWithNull();
    }
    public Uni<Contract> findContractById(String id) {
        if(id == null || id.isEmpty())
            throw new WebApplicationException("Invalid Coords or Contract", Response.Status.BAD_REQUEST);

        return Contract.<Contract>findById(new ObjectId(id)).onFailure(Is404Exception.IS_404).recoverWithNull();
    }

    public Uni<List<Contract>> getContracts(String page, String size, Boolean active, String clientId) {
        List<Object> params = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder();

        if (clientId != null) {
            queryBuilder.append("clientId = ?1");
            params.add(clientId);
        }

        if (active != null) {
            if (!params.isEmpty()) {
                queryBuilder.append(" and ");
            }
            queryBuilder.append("active = ?").append(params.size() + 1);
            params.add(active);
        }

        String query = queryBuilder.toString();
        ReactivePanacheQuery<Contract> panacheQuery;

        if (query.isEmpty()) {
            panacheQuery = Contract.findAll();
        } else {
            panacheQuery = Contract.find(query, params.toArray());
        }

        int pageNumber = (page != null) ? Integer.parseInt(page) : defaultPage;
        int pageSize = (size != null) ? Integer.parseInt(size) : defaultSize;
        panacheQuery.page(Page.of(pageNumber, pageSize));

        return panacheQuery.list()
                .onFailure(Is404Exception.IS_404)
                .recoverWithNull();
    }
}
