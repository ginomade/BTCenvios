package com.gino.btcenvios.btcenvios.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author gino.ghiotto
 */
public class Rates {
    @JsonProperty("rates")
    private List<Rate> rates;
}
