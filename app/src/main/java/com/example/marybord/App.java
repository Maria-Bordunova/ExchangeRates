package com.example.marybord;
/**
 * @author Mary Bordunova
 * @date 8/13/2020
 * @description Application file
 */

import android.app.Application;

import com.example.marybord.di.component.DaggerAppComponent;
import com.example.marybord.di.component.AppComponent;
import com.example.marybord.di.module.AppModule;
import com.example.marybord.di.module.NetModule;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Dependency Injection
        appComponent = DaggerAppComponent.builder().
                appModule(new AppModule(this)).
                netModule(new NetModule()).build();
    }

    public AppComponent getAppComponent() {
        if (appComponent != null)
            return appComponent;
        else
            return null;
    }
}