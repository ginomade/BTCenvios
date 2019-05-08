package com.gino.btcenvios.btcenvios.net;

import retrofit2.Retrofit;

/**
 * @author gino.ghiotto
 */
public class GetRatesServiceImpl {

    private String RATES_SERVICE_URL = "https://ripio.com/api/v1/rates/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RATES_SERVICE_URL)
            .build();

    public GetRatesService service = retrofit.create(GetRatesService.class);

}
