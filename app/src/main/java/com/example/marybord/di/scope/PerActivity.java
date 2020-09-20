package com.example.marybord.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author Mary Bordunova
 * @date 8/11/2020
 * @description Scope for MainActivity
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
