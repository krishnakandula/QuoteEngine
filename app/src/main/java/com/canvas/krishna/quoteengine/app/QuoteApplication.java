package com.canvas.krishna.quoteengine.app;

import android.app.Application;

import com.canvas.krishna.quoteengine.modules.AppComponent;
import com.canvas.krishna.quoteengine.modules.AppModule;
import com.canvas.krishna.quoteengine.modules.DaggerAppComponent;

/**
 * Created by Krishna Chaitanya Kandula on 4/30/17.
 */

public class QuoteApplication extends Application{
    private AppComponent appComponent;

    public AppComponent getAppComponent(){
        return appComponent;
    }

    protected AppComponent initDagger(QuoteApplication application){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = initDagger(this);
    }
}
