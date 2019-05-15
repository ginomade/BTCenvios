package com.gino.btcenvios.btcenvios.net;

import com.gino.btcenvios.btcenvios.model.Rate;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author gino.ghiotto
 */
public interface GetRatesService {
    String RATES_SERVICE_URL = "https://ripio.com/api/v1/rates/";

    @GET(".")
    Call<Rate> listRates();
}
