package com.vn.quochuyapplication.base.di.module

import android.app.Activity
import androidx.fragment.app.Fragment
import com.vn.quochuyapplication.base.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: Fragment) {
    @Provides
    @FragmentScope
    fun provideActivity(): Activity? {
        return fragment.activity
    }
}