package com.vn.quochuyapplication.base

import android.os.Handler
import android.os.Looper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class RxPresenter<T : BaseView> : BasePresenter<T> {

    protected var mView: T? = null
    private var mCompositeDisposable: CompositeDisposable? = null
    private var mHandler: Handler? = null

    override fun attachView(view: T) {
        mView = view
    }

    override fun detachView() {
        mView = null
        unSubscribe()
    }

    private fun unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable!!.clear()
        }
    }

    protected fun addSubscribe(subscription: Disposable?) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable!!.add(subscription!!)
    }

    open val mainHandler: Handler?
        get() {
            if (mHandler == null) {
                mHandler = Handler(Looper.getMainLooper())
            }
            return mHandler
        }
}