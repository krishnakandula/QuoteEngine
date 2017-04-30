package com.canvas.krishna.quoteengine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.canvas.krishna.quoteengine.app.QuoteApplication;
import com.canvas.krishna.quoteengine.models.Quote;
import com.canvas.krishna.quoteengine.network.QuoteApi;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Inject
    QuoteApi quoteApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((QuoteApplication) getApplication()).getAppComponent().inject(this);

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
