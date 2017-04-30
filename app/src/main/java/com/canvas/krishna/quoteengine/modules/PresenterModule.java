package com.canvas.krishna.quoteengine.modules;

import android.content.Context;

import com.canvas.krishna.quoteengine.ui.MainContract;
import com.canvas.krishna.quoteengine.ui.MainPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Krishna Chaitanya Kandula on 4/30/17.
 */
@Module
public class PresenterModule {

    @Provides
    @Singleton
    MainContract.Presenter provideMainPresenter(Context context){
        return new MainPresenter(context);
    }
}
