package com.vn.quochuyapplication.base.di.component

import com.vn.quochuyapplication.QHApplication
import com.vn.quochuyapplication.base.di.module.AppModule
import com.vn.quochuyapplication.base.di.module.DBModule
import com.vn.quochuyapplication.base.model.DataManager
import com.vn.quochuyapplication.base.model.PrefImpl
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DBModule::class])
interface AppComponent {

    fun inject(app: QHApplication)

    fun getDataManager(): DataManager?
    fun getPrefHelper(): PrefImpl?
}