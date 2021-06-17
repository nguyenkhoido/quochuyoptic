package com.vn.quochuyapplication

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.google.gson.Gson
import com.vn.quochuyapplication.base.di.component.AppComponent
import com.vn.quochuyapplication.base.di.component.DaggerAppComponent
import com.vn.quochuyapplication.base.di.module.AppModule
import com.vn.quochuyapplication.base.di.module.DBModule
import io.realm.Realm

class QHApplication : Application(), Application.ActivityLifecycleCallbacks {
    var mAppComponent: AppComponent? = null
    private lateinit var mGSon: Gson

    override fun onCreate() {
        super.onCreate()
        sInstance = this
        mGSon = Gson()
        mAppComponent = getAppComponent()
        mAppComponent?.inject(this)
        registerActivityLifecycleCallbacks(this)
        Realm.init(this)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }


    override fun onActivityPaused(activity: Activity) {

    }


    override fun onActivityStarted(activity: Activity) {
    }


    override fun onActivityDestroyed(activity: Activity) {
    }


    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }


    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
    }


    override fun onActivityResumed(activity: Activity) {
    }

    private fun getAppComponent(): AppComponent? {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                .appModule(AppModule(sInstance))
                .dBModule(DBModule()).build()
        }
        return mAppComponent
    }

    fun getGSon(): Gson {
        return mGSon
    }

    companion object {
        private lateinit var sInstance: QHApplication
        fun getInstance(): QHApplication {
            return sInstance
        }
    }
}