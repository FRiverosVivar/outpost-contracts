package com.friveros.newtech.dto;

import com.friveros.newtech.enums.CurrencyCodesEnum;

public class MinifiedCurrency {
    public Long numericValue;
    public Long decimals;
    public CurrencyCodesEnum currencyCode;

    public Long getNumericValue() {
        return numericValue;
    }

    public void setNumericValue(Long numericValue) {
        this.numericValue = numericValue;
    }

    public Long getDecimals() {
        return decimals;
    }

    public void setDecimals(Long decimals) {
        this.decimals = decimals;
    }

    public CurrencyCodesEnum getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(CurrencyCodesEnum currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return "MinifiedCurrency{" +
                "numericValue=" + numericValue +
                ", decimals=" + decimals +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }
}
