package com.homewise.android.ui.base

/**
 * Created by @raj on 27/12/17.
 */
interface MvpPresenter<V : MvpView> {
    fun onAttach(mvpView: V)

    fun onDetach()

    fun handleApiError()
}