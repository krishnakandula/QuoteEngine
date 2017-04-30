package com.canvas.krishna.quoteengine.modules;

import com.canvas.krishna.quoteengine.app.Constants;
import com.canvas.krishna.quoteengine.network.QuoteApi;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Krishna Chaitanya Kandula on 4/29/17.
 */
@Module
public class NetworkModule {
    private static final String NAME_BASE_URL = "base_url";
    private static final String API_KEY_VALUE = "X-Mashape-Key";

    @Provides
    @Named(NAME_BASE_URL)
    String provideBaseUrlString(){
        return Constants.API_BASE_URL;
    }

    @Provides
    Converter.Factory providesConverterFactory(){
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Converter.Factory converter, @Named(NAME_BASE_URL) String baseUrl){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header(API_KEY_VALUE, Constants.API_KEY)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }

    @Provides
    @Singleton
    QuoteApi provideQuoteApi(Retrofit retrofit){
        return retrofit.create(QuoteApi.class);
    }
}
