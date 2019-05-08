package com.gino.btcenvios.btcenvios.net;

import com.gino.btcenvios.btcenvios.model.Fees;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author gino.ghiotto
 */
public interface GetFeesService {
    @GET("users/{user}/repos")
    Call<List<Fees>> listRepos();
}
