package com.homewise.android.injection.component;

import com.homewise.android.injection.PerActivity;
import com.homewise.android.injection.module.ActivityModule;
import com.homewise.android.ui.home.HomeActivity

import dagger.Component;

/**
 * Created by Shashank on 28/09/17.
 */

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(homeActivity: HomeActivity)
}
