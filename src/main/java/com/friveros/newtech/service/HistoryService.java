package com.friveros.newtech.service;

import com.friveros.newtech.Contract;
import com.friveros.newtech.History;
import com.friveros.newtech.dto.Point;
import com.friveros.newtech.enums.ActionEnums;
import com.friveros.newtech.mapper.HistoryMapper;
import io.quarkus.security.identity.SecurityIdentity;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class HistoryService {
    @Inject
    JsonWebToken jwt;
    @Inject
    SecurityIdentity identity;
    private final HistoryMapper historyMapper;
    private final SequenceGeneratorService sequenceGeneratorService;

    private static final Logger LOGGER = Logger.getLogger(HistoryService.class.getName());
    public HistoryService(HistoryMapper historyMapper, SequenceGeneratorService sequenceGeneratorService) {
        this.historyMapper = historyMapper;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }
    public Uni<History> persistHistory(History history) {

        history.numberId = sequenceGeneratorService.getNextSequence("historySequence");
        return History.persist(history)
                .replaceWith(history)
                .map(this.historyMapper::toSchema)
                .replaceWith(history)
                .onItem().ignore().andSwitchTo(Uni.createFrom().item(history))
                .onFailure(Is404Exception.IS_404).recoverWithNull();
    }
    public History createHistoryFromContract(String contractId, List<ActionEnums> actions, Point point) {
        LOGGER.info(point.toString());
        return this.createHistory(contractId, identity.getPrincipal().getName(), jwt.getSubject(), actions, point);
    }
    public List<ActionEnums> getActionsFromContract(Contract c1) {
        List<ActionEnums> actions = new ArrayList<>();
        List<Field> fields = Arrays.stream(c1.getClass().getDeclaredFields()).toList();

        fields.forEach((Field field) -> {
            switch(field.getName()) {
                case "active": {
                    if(c1.active != null)
                        actions.add(c1.active ? ActionEnums.ACTIVATED: ActionEnums.DEACTIVATED);
                    break;
                }
                case "companyName": {
                    if(c1.companyName  != null)
                        actions.add(ActionEnums.UPDATED_COMPANY_NAME);
                    break;
                }
                case "safi": {
                    if(c1.safi != null)
                        actions.add(ActionEnums.UPDATED_SAFI);
                    break;
                }
                case "contractName": {
                    if(c1.contractName != null)
                        actions.add(ActionEnums.UPDATED_CONTRACT_NAME);
                    break;
                }
                case "startAt": {
                    if(c1.startAt != null)
                        actions.add(ActionEnums.UPDATED_START_AT);
                    break;
                }
                case "endAt": {
                    if(c1.endAt != null)
                        actions.add(ActionEnums.UPDATED_END_AT);
                    break;
                }

                case "inspectingProsecutorId": {
                    if(c1.inspectingProsecutorId != null)
                        actions.add(ActionEnums.UPDATED_INSPECTOR_PROSECUTOR_ID);
                    break;
                }
                case "residentId": {
                    if(c1.residentId != null)
                        actions.add(ActionEnums.UPDATED_RESIDENT_ID);
                    break;
                }
                case "assignedUsersId": {
                    if(c1.assignedUsersId != null)
                        actions.add(ActionEnums.UPDATED_ASSIGNED_USERS);
                    break;
                }
                case "galleryUri": {
                    if(c1.galleryUri != null)
                        actions.add(ActionEnums.UPDATED_GALLERY_PHOTOS);
                    break;
                }
                case "locations": {
                    if(c1.locations != null)
                        actions.add(ActionEnums.UPDATED_LOCATIONS);
                    break;
                }
                case "clientId": {
                    if(c1.clientId != null)
                        actions.add(ActionEnums.UPDATED_CLIENT_ID);
                    break;
                }
                case "deliveryClientId": {
                    if(c1.deliveryClientId != null)
                        actions.add(ActionEnums.UPDATED_DELIVERY_CLIENT_ID);
                    break;
                }
                case "resolutionName": {
                    if(c1.resolutionName != null)
                        actions.add(ActionEnums.UPDATED_RESOLUTION_NAME);
                    break;
                }
                case "budget": {
                    if(c1.budget != null)
                        actions.add(ActionEnums.UPDATED_BUDGET);
                    break;
                }
                case "offer": {
                    if(c1.offer != null)
                        actions.add(ActionEnums.UPDATED_OFFER);
                    break;
                }
                case "modality": {
                    if(c1.modality != null)
                        actions.add(ActionEnums.UPDATED_MODALITY);
                    break;
                }
                case "baseIndex": {
                    if(c1.baseIndex != null)
                        actions.add(ActionEnums.UPDATED_BASE_INDEX);
                    break;
                }
                case "reAdjustment": {
                    if(c1.reAdjustment != null)
                        actions.add(ActionEnums.UPDATED_READJUSTMENT);
                    break;
                }
                case "currency": {
                    if(c1.currency != null)
                        actions.add(ActionEnums.UPDATED_CURRENCY);
                    break;
                }

            }
        });
        return actions;
    }
    private History createHistory(String contractId, String userName, String userId, List<ActionEnums> actions, Point point) {
        History history = new History(contractId, Instant.now(), userName, userId, actions, point);
        LOGGER.info(history.toString());
        return history;
    }
}
