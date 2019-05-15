package com.gino.btcenvios.btcenvios.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author gino.ghiotto
 */
public class Rate {

    @JsonProperty("rates")
    private Rates rates;

    @JsonProperty("base")
    private String base;

    public Rates getRates() {
        return rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

}
