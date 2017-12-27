package com.homewise.android.injection

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * Created by @raj on 27/12/17.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class PerActivity