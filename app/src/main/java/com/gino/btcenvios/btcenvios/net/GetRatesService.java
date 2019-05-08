package com.gino.btcenvios.btcenvios.net;

import com.gino.btcenvios.btcenvios.model.Rates;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author gino.ghiotto
 */
public interface GetRatesService {
    @GET("users/{user}/repos")
    Call<List<Rates>> listRates();
}
