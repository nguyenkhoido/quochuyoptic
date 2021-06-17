package com.vn.quochuyapplication.base.di.component

import com.vn.quochuyapplication.base.di.module.ActivityModule
import com.vn.quochuyapplication.base.di.scope.ActivityScope
import com.vn.quochuyapplication.ui.company.CompanyActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(companyActivity: CompanyActivity)
}