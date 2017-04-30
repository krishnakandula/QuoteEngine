package com.canvas.krishna.quoteengine.ui;

import android.content.Context;
import android.util.Log;

import com.canvas.krishna.quoteengine.app.QuoteApplication;
import com.canvas.krishna.quoteengine.models.Quote;
import com.canvas.krishna.quoteengine.network.QuoteApi;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Krishna Chaitanya Kandula on 4/30/17.
 */

public class MainPresenter implements MainContract.Presenter{
    private static final String LOG_TAG = MainPresenter.class.getSimpleName();

    @Inject
    QuoteApi quoteApi;

    private MainContract.View view;

    public MainPresenter(Context context){
        ((QuoteApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
        getQuotes();
    }

    private void getQuotes(){
        quoteApi.getQuotes("famous", 10).enqueue(new Callback<List<Quote>>() {
            @Override
            public void onResponse(Call<List<Quote>> call, Response<List<Quote>> response) {
                if(response.code() != 200){
                    Log.e(LOG_TAG, "Retrofit response error");
                } else {
                    List<Quote> quotes = response.body();
                    Log.v(LOG_TAG, quotes.get(0).toString());
                }
            }

            @Override
            public void onFailure(Call<List<Quote>> call, Throwable t) {
                Log.e(LOG_TAG, "Retrofit response error");
            }
        });
    }
}