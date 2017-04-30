package com.canvas.krishna.quoteengine.modules;

import com.canvas.krishna.quoteengine.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Krishna Chaitanya Kandula on 4/30/17.
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, PresenterModule.class})
public interface AppComponent {
    void inject(MainActivity target);
}
