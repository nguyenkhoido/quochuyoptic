package com.vn.quochuyapplication.base.di.component

import android.app.Activity
import com.vn.quochuyapplication.base.di.module.FragmentModule
import com.vn.quochuyapplication.base.di.scope.FragmentScope
import com.vn.quochuyapplication.ui.customer.CustomerFragment
import com.vn.quochuyapplication.ui.export.ExportFragment
import com.vn.quochuyapplication.ui.input.ImportFragment
import com.vn.quochuyapplication.ui.money.MoneyFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {
    val activity: Activity?

    fun inject(importFragment: ImportFragment)
    fun inject(exportFragment: ExportFragment)
    fun inject(moneyFragment: MoneyFragment)
    fun inject(customerFragment: CustomerFragment)
}