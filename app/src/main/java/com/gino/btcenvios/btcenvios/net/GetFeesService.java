package com.gino.btcenvios.btcenvios.net;

import com.gino.btcenvios.btcenvios.model.Fees;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author gino.ghiotto
 */
public interface GetFeesService {
    String FEES_SERVICE_URL = "https://bitcoinfees.earn.com/api/v1/fees/";

    @GET("recommended")
    Call<Fees> listFees();
}
