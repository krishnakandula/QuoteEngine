package com.canvas.krishna.quoteengine.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.canvas.krishna.quoteengine.R;
import com.canvas.krishna.quoteengine.app.QuoteApplication;
import com.canvas.krishna.quoteengine.models.Quote;
import com.canvas.krishna.quoteengine.network.QuoteApi;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainContract.View{
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Inject
    MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((QuoteApplication) getApplication()).getAppComponent().inject(this);
        presenter.setView(this);
        presenter.start();
    }
}
