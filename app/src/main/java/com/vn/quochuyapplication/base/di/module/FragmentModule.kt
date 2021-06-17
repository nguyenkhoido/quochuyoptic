package com.vn.quochuyapplication.base.di.module

import android.app.Activity
import androidx.fragment.app.Fragment
import com.vn.quochuyapplication.base.di.scope.FragmentScope
import com.vn.quochuyapplication.ui.customer.CustomerFragment
import com.vn.quochuyapplication.ui.export.ExportFragment
import com.vn.quochuyapplication.ui.input.ImportFragment
import com.vn.quochuyapplication.ui.money.MoneyFragment
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(val fragment: Fragment) {
    @Provides
    @FragmentScope
    fun provideActivity(): Activity? {
        return fragment.activity
    }

    @Provides
    @FragmentScope
    fun provideImportFragment(): ImportFragment {
        return ImportFragment.newInstance()
    }

    @Provides
    @FragmentScope
    fun provideExportFragment(): ExportFragment {
        return ExportFragment.newInstance()
    }

    @Provides
    @FragmentScope
    fun provideMoneyFragment(): MoneyFragment {
        return MoneyFragment.newInstance()
    }

    @Provides
    @FragmentScope
    fun provideCustomerFragment(): CustomerFragment {
        return CustomerFragment.newInstance()
    }

}