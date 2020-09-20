package com.example.marybord.di.module;
/**
 * @author Mary Bordunova
 * @date 8/13/2020
 * @description This module provides Application for getting context from NetModule
 */
import android.app.Application;

import com.example.marybord.di.scope.PerApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @PerApplication
    @Provides
    Application provideApplication() {
        return application;
    }
}
