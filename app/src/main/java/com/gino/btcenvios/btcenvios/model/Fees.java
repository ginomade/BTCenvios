package com.gino.btcenvios.btcenvios.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author gino.ghiotto
 */
public class Fees {
    @JsonProperty("fastestFee")
    private String fastestFee;

    @JsonProperty("hourFee")
    private String hourFee;

    @JsonProperty("halfHourFee")
    private String halfHourFee;

    public String getFastestFee ()
    {
        return fastestFee;
    }

    public void setFastestFee (String fastestFee)
    {
        this.fastestFee = fastestFee;
    }

    public String getHourFee ()
    {
        return hourFee;
    }

    public void setHourFee (String hourFee)
    {
        this.hourFee = hourFee;
    }

    public String getHalfHourFee ()
    {
        return halfHourFee;
    }

    public void setHalfHourFee (String halfHourFee)
    {
        this.halfHourFee = halfHourFee;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [fastestFee = "+fastestFee+", hourFee = "+hourFee+", halfHourFee = "+halfHourFee+"]";
    }
}
