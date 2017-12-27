package com.homewise.android;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.homewise.android.data.AppDataManager;
import com.homewise.android.injection.component.ApplicationComponent;
import com.homewise.android.injection.component.DaggerApplicationComponent;
import com.homewise.android.injection.module.ApplicationModule;

import javax.inject.Inject;

import io.fabric.sdk.android.Fabric;

/**
 * Created by @raj on 26/12/17.
 */
public class HomeWiseApplication extends Application{

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);

    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }

    public static HomeWiseApplication get(Context context) {
        return (HomeWiseApplication) context.getApplicationContext();
    }


}