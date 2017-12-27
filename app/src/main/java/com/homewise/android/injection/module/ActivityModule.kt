package com.homewise.android.injection.module;

import android.app.Activity;
import android.content.Context;
import com.homewise.android.injection.ActivityContext

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Shashank on 28/09/17.
 */
@Module
class ActivityModule(activity: Activity) {

    private var mActivity: Activity = activity

    @Provides
    @ActivityContext
    internal fun provideActivityContext(): Context {
        return mActivity
    }

    @Provides
    internal fun provideActivity(): Activity {
        return mActivity
    }

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }
}