package com.vn.quochuyapplication.base.model

import com.google.gson.JsonParseException
import com.vn.quochuyapplication.base.BaseView
import io.reactivex.exceptions.CompositeException
import io.reactivex.subscribers.ResourceSubscriber
import java.lang.ref.WeakReference
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException


abstract class CommonSubscriber<O> : ResourceSubscriber<O> {

    private var weakReference: WeakReference<BaseView>? = null
    private var apiFunction: String? = null
    private var isShowError = true

    protected constructor ()

    protected constructor(view: BaseView) {
        this.weakReference = WeakReference(view)
    }

    protected constructor(view: BaseView, isShowError: Boolean) {
        this.weakReference = WeakReference(view)
        this.isShowError = isShowError
    }

    protected constructor(apiFunction: String, view: BaseView) {
        this.weakReference = WeakReference(view)
        this.apiFunction = apiFunction
    }

    protected constructor(apiFunction: String, view: BaseView, isShowError: Boolean) {
        this.weakReference = WeakReference(view)
        this.apiFunction = apiFunction
        this.isShowError = isShowError
    }

    override fun onComplete() {
        if (weakReference != null) {
            weakReference!!.get()?.stateCompleted()
        }
    }

    override fun onNext(t: O) {

    }

    override fun onError(e: Throwable) {
        if (weakReference != null) {
            val view = weakReference!!.get()
            if (view != null) {
                view.stateError()
                errorHanding(apiFunction, e, view, isShowError)
            }
        }
    }

    private fun errorHanding(
        apiFunction: String?,
        e: Throwable,
        view: BaseView?,
        isShowError: Boolean
    ) {

        if (view == null) {
            return
        }

        view.stateCompleted()
    }
}
