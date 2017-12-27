package com.homewise.android.injection.module

import android.app.Fragment
import android.content.Context
import com.homewise.android.injection.ActivityContext
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by @raj on 27/12/17.
 */
@Module
class FragmentModule(fragment: Fragment) {
    private var mFragment: Fragment = fragment

    @Provides
    internal fun provideActivity(): Fragment {
        return mFragment
    }

    @Provides
    @ActivityContext
    internal fun providesContext(): Context {
        return mFragment.activity
    }

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }
}