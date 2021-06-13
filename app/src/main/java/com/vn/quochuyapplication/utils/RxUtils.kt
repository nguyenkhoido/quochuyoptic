package com.vn.quochuyapplication.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RxUtils {
    fun <T> rxSchedulerHelper(): FlowableTransformer<T, T> {
        return FlowableTransformer { flowable: Flowable<T> ->
            flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> rxBackgroundSchedulerHelper(): FlowableTransformer<T, T> {
        return FlowableTransformer { flowable: Flowable<T> ->
            flowable.subscribeOn(Schedulers.newThread())
        }
    }

}