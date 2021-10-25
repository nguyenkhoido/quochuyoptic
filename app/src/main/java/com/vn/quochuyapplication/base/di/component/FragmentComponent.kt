package com.vn.quochuyapplication.base.di.component

import android.app.Activity
import androidx.fragment.app.Fragment
import com.vn.quochuyapplication.base.di.module.FragmentModule
import com.vn.quochuyapplication.base.di.scope.FragmentScope
import com.vn.quochuyapplication.ui.company.product.FragmentFrame
import com.vn.quochuyapplication.ui.company.product.FragmentGlass
import com.vn.quochuyapplication.ui.company.product.FragmentProduct
import com.vn.quochuyapplication.ui.customer.CustomerFragment
import com.vn.quochuyapplication.ui.export.ExportFragment
import com.vn.quochuyapplication.ui.input.ImportFragment
import com.vn.quochuyapplication.ui.money.MoneyFragment
import com.vn.quochuyapplication.ui.qrcode.ProductIdFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {
    val activity: Activity?

    fun inject(importFragment: ImportFragment)
    fun inject(exportFragment: ExportFragment)
    fun inject(moneyFragment: MoneyFragment)
    fun inject(customerFragment: CustomerFragment)
    fun inject(productIdFragment: ProductIdFragment)

    fun inject(productFragment: FragmentProduct)
    fun inject(frameFragment: FragmentFrame)
    fun inject(glassFragment: FragmentGlass)
}