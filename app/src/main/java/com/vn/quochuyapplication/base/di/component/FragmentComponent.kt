package com.vn.quochuyapplication.base.di.component

import com.vn.quochuyapplication.base.di.module.FragmentModule
import com.vn.quochuyapplication.base.di.scope.FragmentScope
import dagger.Component

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {

}