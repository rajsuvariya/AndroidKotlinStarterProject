package com.homewise.android.injection

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Qualifier

/**
 * Created by @raj on 27/12/17.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
annotation class ActivityContext
