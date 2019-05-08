package com.gino.btcenvios.btcenvios.net;

import retrofit2.Retrofit;

/**
 * @author gino.ghiotto
 */
public class GetFeesServiceImpl {

    private String FEES_SERVICE_URL = "https://bitcoinfees.earn.com/api/v1/fees/recommended";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(FEES_SERVICE_URL)
            .build();

    GetFeesService service = retrofit.create(GetFeesService.class);

}
