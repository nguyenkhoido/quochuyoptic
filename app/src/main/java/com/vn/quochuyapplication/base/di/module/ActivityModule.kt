package com.vn.quochuyapplication.base.di.module

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.vn.quochuyapplication.base.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val mActivity: AppCompatActivity) {
    @Provides
    @ActivityScope
    fun provideActivity(): Activity {
        return mActivity
    }
}