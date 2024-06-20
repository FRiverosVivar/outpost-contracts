package com.friveros.newtech.resource;

import com.friveros.newtech.Contract;
import com.friveros.newtech.dto.ContractDTO;
import com.friveros.newtech.service.ContractService;
import io.smallrye.mutiny.Uni;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.apache.camel.Body;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.resteasy.reactive.NoCache;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.eclipse.microprofile.openapi.annotations.enums.SchemaType.ARRAY;
import static org.eclipse.microprofile.openapi.annotations.enums.SchemaType.OBJECT;
@Path("/api/contracts")
public class ContractResource {
    private final ContractService contractService;

    public ContractResource(ContractService contractService) {
        this.contractService = contractService;
    }

    @POST
    @Path("")
    @Operation(summary = "Creates a new Contract for a user")
    @APIResponse(
            responseCode = "200",
            description = "Creates a Contract",
            content = @Content(
                    mediaType = APPLICATION_JSON,
                    schema = @Schema(implementation = Contract.class, type = OBJECT)
            )
    )
    public Uni<Contract> createContract(@Body @Valid ContractDTO contractDTO) {
        if(contractDTO == null)
            throw new WebApplicationException("Invalid Contract DTO", Response.Status.BAD_REQUEST);

        return this.contractService.persistContract(contractDTO.getContract(), contractDTO.getPoint());
    }
    @DELETE
    @Path("/{id}")
    @NoCache
    @Operation(summary = "Removes a Contract")
    @APIResponse(
            responseCode = "200",
            description = "Contract removed successfully",
            content = @Content(
                    mediaType = APPLICATION_JSON,
                    schema = @Schema(implementation = Contract.class, type = OBJECT)
            )
    )
    public Uni<Contract> removeContractByContractId(@PathParam("id") String id) {
        return this.contractService.removeContractById(id);
    }
    @PUT
    @Path("/{id}")
    @Operation(summary = "Updates a Contract")
    @APIResponse(
            responseCode = "200",
            description = "Updates a Contract",
            content = @Content(
                    mediaType = APPLICATION_JSON,
                    schema = @Schema(implementation = Contract.class, type = OBJECT)
            )
    )
    public Uni<Contract> updateContract(@PathParam("id") String id, @Body @Valid ContractDTO contractDTO) {
        return this.contractService.updateContract(contractDTO.getContract(), contractDTO.getPoint());
    }
    @GET
    @Path("/{id}")
    @NoCache
    @Operation(summary = "Find a contract by their ID")
    @APIResponse(
            responseCode = "200",
            description = "Contract found successfully",
            content = @Content(
                    mediaType = APPLICATION_JSON,
                    schema = @Schema(implementation = Contract.class, type = OBJECT)
            )
    )
    public Uni<Contract> findContractById(@PathParam("id") String id) {
        return this.contractService.findContractById(id);
    }
    @GET
    @Path("")
    @Operation(summary = "Returns all Active Contracts")
    @APIResponse(
            responseCode = "200",
            description = "Gets all Contracts, or empty list if none and can be filtered by active and clientId properties.",
            content = @Content(
                    mediaType = APPLICATION_JSON,
                    schema = @Schema(implementation = Contract.class, type = ARRAY)
            )
    )
    public Uni<List<Contract>> getPaginatedContracts(@QueryParam("page") String page, @QueryParam("size") String size, @QueryParam("active") Boolean active, @QueryParam("clientId") String clientId) {
        return this.contractService.getContracts(page, size, active, clientId);
    }
//    @GET
//    @Path("/client")
//    @Operation(summary = "Returns all the Contracts from a clientId")
//    @APIResponse(
//            responseCode = "200",
//            description = "Gets all Contracts from a clientId, or empty list if none",
//            content = @Content(
//                    mediaType = APPLICATION_JSON,
//                    schema = @Schema(implementation = Contract.class, type = ARRAY)
//            )
//    )
//    public Uni<List<Contract>> findContractsByClientId(@QueryParam("clientId") String clientId, @QueryParam("active") Boolean active) {
//        return this.contractService.findContractsByClientId(clientId,active != null);
//    }
}
