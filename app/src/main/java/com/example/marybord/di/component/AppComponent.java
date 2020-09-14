package com.example.marybord.di.component;
/**
 * @author Mary Bordunova
 * @date 8/13/2020
 * @description The most important, root scope, which lives the longest.
 */
import com.example.marybord.di.module.AppModule;
import com.example.marybord.di.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {

    Retrofit retrofit();
}

