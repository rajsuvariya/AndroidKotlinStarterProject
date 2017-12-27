package com.homewise.android.injection.component;

import android.content.Context;

import com.homewise.android.HomeWiseApplication;
import com.homewise.android.data.DataManager;
import com.homewise.android.injection.ApplicationContext;
import com.homewise.android.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by @raj on 27/12/17.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(homeWiseApplication: HomeWiseApplication)

    fun getDataManager(): DataManager

}
