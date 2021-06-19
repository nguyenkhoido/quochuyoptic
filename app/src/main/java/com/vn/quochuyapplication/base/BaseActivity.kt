package com.vn.quochuyapplication.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewbinding.ViewBinding
import com.vn.quochuyapplication.QHApplication.Companion.getInstance
import com.vn.quochuyapplication.base.di.component.ActivityComponent
import com.vn.quochuyapplication.base.di.component.DaggerActivityComponent
import javax.inject.Inject

abstract class BaseActivity<T : BasePresenter<*>> : SimpleActivity(), BaseView {
    @Inject
    lateinit var mPresenter: T
    protected abstract fun initInject()

    protected val activityComponent: ActivityComponent
        get() = DaggerActivityComponent.builder()
            .appComponent(getInstance().mAppComponent)
            .build()




    override fun onViewCreated() {
        super.onViewCreated()
        initInject()
        //if (mPresenter != null) mPresenter!!.attachView(mView)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        if (mPresenter != null) mPresenter!!.detachView()
        super.onDestroy()
    }

    fun showError(apiFunction: String?, errorType: Int, isShowError: Boolean) {}
    override fun stateLoading() {}
    override fun stateError() {}
    override fun stateCompleted() {}
    override fun displayError(message: String) {}
    override fun showError(message: String?) {}

}