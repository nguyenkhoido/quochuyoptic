package com.vn.quochuyapplication.base

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.vn.quochuyapplication.QHApplication.Companion.getInstance
import com.vn.quochuyapplication.base.di.component.DaggerFragmentComponent
import com.vn.quochuyapplication.base.di.component.FragmentComponent
import com.vn.quochuyapplication.base.di.module.FragmentModule
import javax.inject.Inject

abstract class BaseFragment<T : BasePresenter<*>> : SimpleFragment(), BaseView {
    @Inject
    lateinit var presenter: T

    private var dialog: Dialog? = null
    private var mToastError: Toast? = null

    protected abstract fun initInject()

    fun fragmentComponent(): FragmentComponent {
      return DaggerFragmentComponent.builder()
            .appComponent(getInstance().mAppComponent)
            .fragmentModule(fragmentModule)
            .build()
    }
    val fragmentModule: FragmentModule
         get() = FragmentModule(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInject()
        //presenter!!.attachView(this)
    }

    override fun onDestroyView() {
        presenter!!.detachView()
        super.onDestroyView()
    }

    override fun showError(message: String?) {
    }

    override fun stateError() {}
    override fun displayError(message: String) {}
    override fun stateCompleted() {
    }

    override fun stateLoading() {
    }
}