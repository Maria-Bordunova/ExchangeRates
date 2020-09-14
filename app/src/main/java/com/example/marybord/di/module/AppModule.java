package com.example.marybord.di.module;
/**
 * @author Mary Bordunova
 * @date 8/13/2020
 * @description This module provides Application for getting context from NetModule
 */
import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }
}
