package com.example.marybord.di.module;
/**
 * @author Mary Bordunova
 * @date 8/13/2020
 * @description Module for MainActivity . It's a part of ActivityComponent
 */

import com.example.marybord.di.scope.PerActivity;
import com.example.marybord.mvp.CurrencyContract;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final CurrencyContract.View view;

    public ActivityModule(CurrencyContract.View view) {
        this.view = view;
    }

    @PerActivity
    @Provides
    CurrencyContract.View provideMainView() {
        return view;
    }
}
