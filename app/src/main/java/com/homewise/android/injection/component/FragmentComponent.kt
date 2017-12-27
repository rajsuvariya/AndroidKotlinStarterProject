package com.homewise.android.injection.component

import com.homewise.android.injection.PerActivity
import com.homewise.android.injection.module.FragmentModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by @raj on 27/12/17.
 */
@PerActivity
@Component (dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(FragmentModule::class))
interface FragmentComponent {
}