package com.friveros.newtech.dto;

import com.friveros.newtech.Contract;
import jakarta.validation.constraints.NotNull;

public class ContractDTO {
    @NotNull()
    public Point point;
    @NotNull()
    public Contract contract;

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    @Override
    public String toString() {
        return "ContractDTO{" +
                "point=" + point +
                ", contract=" + contract +
                '}';
    }
}
