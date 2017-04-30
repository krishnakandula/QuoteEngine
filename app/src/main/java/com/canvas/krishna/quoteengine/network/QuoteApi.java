package com.canvas.krishna.quoteengine.network;

import com.canvas.krishna.quoteengine.models.Quote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Krishna Chaitanya Kandula on 4/29/17.
 */

public interface QuoteApi {

    @POST("/?")
    Call<List<Quote>> getQuotes(@Query("cat") String type, @Query("count") int count);
}
