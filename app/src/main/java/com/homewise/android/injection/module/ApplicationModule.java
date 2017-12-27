package com.homewise.android.injection.module;

import android.app.Application;
import android.content.Context;

import com.homewise.android.data.AppDataManager;
import com.homewise.android.data.DataManager;
import com.homewise.android.data.local.AppPreferenceManager;
import com.homewise.android.data.local.PreferenceManager;
import com.homewise.android.data.remote.ApiManager;
import com.homewise.android.data.remote.AppApiManager;
import com.homewise.android.injection.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by @raj on 27/12/17.
 */
@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application app){
        mApplication = app;
    }

    @Provides
    @ApplicationContext
    Context provideApplicationContext()  {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    ApiManager provideApiManager(AppApiManager appApiManager) {
        return appApiManager;
    }

    @Provides
    @Singleton
    PreferenceManager providePreferenceManager(AppPreferenceManager appPreferenceManager) {
        return appPreferenceManager;
    }
}
