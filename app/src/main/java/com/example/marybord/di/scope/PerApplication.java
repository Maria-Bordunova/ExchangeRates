package com.example.marybord.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author Mary Bordunova
 * @date 9/20/2020
 * @description Application Scope
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerApplication {
}
