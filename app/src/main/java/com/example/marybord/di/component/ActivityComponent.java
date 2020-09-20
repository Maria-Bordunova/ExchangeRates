package com.example.marybord.di.component;
/**
 * @author Mary Bordunova
 * @date 8/13/2020
 * @description SupComponent for AppComponent.  Component has the scope PerActivity. Scoped object lives as long as its component it can be seen as Singleton in Subcomponent context
 */

import com.example.marybord.di.module.ActivityModule;
import com.example.marybord.di.scope.PerActivity;
import com.example.marybord.mvp.MainActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity injector);
}
