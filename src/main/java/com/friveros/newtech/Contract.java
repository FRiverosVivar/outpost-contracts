package com.friveros.newtech;

import com.friveros.newtech.dto.MinifiedCurrency;
import com.friveros.newtech.enums.ContractStatus;
import com.friveros.newtech.enums.ModalityEnum;
import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import io.smallrye.common.constraint.NotNull;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.time.Instant;
import java.util.Currency;
import java.util.List;

@MongoEntity(collection = "contracts")
@Tag( description = "contracts")
@Schema(description = "Contract Schema for Contracts")
public class Contract extends ReactivePanacheMongoEntity {
    @NotNull
    public Boolean active;
    @NotNull
    public Instant createdAt;
    @NotNull
    public Long numberId;
    @NotNull
    public String companyName;
    @NotNull
    public ContractStatus status;
    @NotNull
    public String safi;
    @NotNull
    public String contractName;
    @NotNull
    public Instant startAt;
    @NotNull
    public Instant endAt;
    @NotNull
    public String inspectingProsecutorId;
    @NotNull
    public String residentId;
    @NotNull
    public List<String> assignedUsersId;
    @NotNull
    public List<String> galleryUri;
    @NotNull
    public List<Location> locations;
    @NotNull
    public String clientId;
    @NotNull
    public String deliveryClientId;
    @NotNull
    public String resolutionName;
    @NotNull
    public Long budget;
    @NotNull
    public Long offer;
    @NotNull
    public ModalityEnum modality;
    @NotNull
    public String baseIndex;
    @NotNull
    public MinifiedCurrency reAdjustment;
    @NotNull
    public MinifiedCurrency currency;

    public ContractStatus getStatus() {
        return status;
    }

    public void setStatus(ContractStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSafi() {
        return safi;
    }

    public void setSafi(String safi) {
        this.safi = safi;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public Instant getStartAt() {
        return startAt;
    }

    public void setStartAt(Instant startAt) {
        this.startAt = startAt;
    }

    public Instant getEndAt() {
        return endAt;
    }

    public void setEndAt(Instant endAt) {
        this.endAt = endAt;
    }

    public String getInspectingProsecutorId() {
        return inspectingProsecutorId;
    }

    public void setInspectingProsecutorId(String inspectingProsecutorId) {
        this.inspectingProsecutorId = inspectingProsecutorId;
    }

    public String getResidentId() {
        return residentId;
    }

    public void setResidentId(String residentId) {
        this.residentId = residentId;
    }

    public List<String> getAssignedUsersId() {
        return assignedUsersId;
    }

    public void setAssignedUsersId(List<String> assignedUsersId) {
        this.assignedUsersId = assignedUsersId;
    }

    public List<String> getGalleryUri() {
        return galleryUri;
    }

    public void setGalleryUri(List<String> galleryUri) {
        this.galleryUri = galleryUri;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getDeliveryClientId() {
        return deliveryClientId;
    }

    public void setDeliveryClientId(String deliveryClientId) {
        this.deliveryClientId = deliveryClientId;
    }

    public String getResolutionName() {
        return resolutionName;
    }

    public void setResolutionName(String resolutionName) {
        this.resolutionName = resolutionName;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public Long getOffer() {
        return offer;
    }

    public void setOffer(Long offer) {
        this.offer = offer;
    }

    public ModalityEnum getModality() {
        return modality;
    }

    public void setModality(ModalityEnum modality) {
        this.modality = modality;
    }

    public String getBaseIndex() {
        return baseIndex;
    }

    public void setBaseIndex(String baseIndex) {
        this.baseIndex = baseIndex;
    }

    public MinifiedCurrency getReAdjustment() {
        return reAdjustment;
    }

    public void setReAdjustment(MinifiedCurrency reAdjustment) {
        this.reAdjustment = reAdjustment;
    }

    public MinifiedCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(MinifiedCurrency currency) {
        this.currency = currency;
    }

    public Long getNumberId() {
        return numberId;
    }

    public void setNumberId(Long numberId) {
        this.numberId = numberId;
    }

    @Override
    public String
    toString() {
        return "Contract{" +
                "active=" + active +
                ", createdAt=" + createdAt +
                ", numberId=" + numberId +
                ", companyName='" + companyName + '\'' +
                ", status=" + status +
                ", safi='" + safi + '\'' +
                ", contractName='" + contractName + '\'' +
                ", startAt=" + startAt +
                ", endAt=" + endAt +
                ", inspectingProsecutorId='" + inspectingProsecutorId + '\'' +
                ", residentId='" + residentId + '\'' +
                ", assignedUsersId=" + assignedUsersId +
                ", galleryUri=" + galleryUri +
                ", locations=" + locations +
                ", clientId='" + clientId + '\'' +
                ", deliveryClientId='" + deliveryClientId + '\'' +
                ", resolutionName='" + resolutionName + '\'' +
                ", budget=" + budget +
                ", offer=" + offer +
                ", modality=" + modality +
                ", baseIndex='" + baseIndex + '\'' +
                ", reAdjustment=" + reAdjustment +
                ", currency=" + currency +
                '}';
    }
}
