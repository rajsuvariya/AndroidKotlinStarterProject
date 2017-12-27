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
class ApplicationModule(private val mApplication: Application) {

    @Provides
    @ApplicationContext
    internal fun provideApplicationContext(): Context {
        return mApplication
    }

    @Provides
    internal fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @Singleton
    internal fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    internal fun provideApiManager(appApiManager: AppApiManager): ApiManager {
        return appApiManager
    }

    @Provides
    @Singleton
    internal fun providePreferenceManager(appPreferenceManager: AppPreferenceManager): PreferenceManager {
        return appPreferenceManager
    }
}
