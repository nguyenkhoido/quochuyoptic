package com.vn.quochuyapplication.base.di.module

import android.app.Application
import com.vn.quochuyapplication.base.model.AppDbHelper
import com.vn.quochuyapplication.base.model.DataManager
import com.vn.quochuyapplication.base.model.PrefImpl
import com.vn.quochuyapplication.base.model.PreferenceHelper
import com.vn.quochuyapplication.data.db.DBHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {
    @Provides
    @Singleton
    fun provideApplicationContext(): Application {
        return application
    }

    @Provides
    @Singleton
    fun providePreferenceHelper(prefImpl: PrefImpl): PreferenceHelper {
        return prefImpl
    }

    @Provides
    @Singleton
    fun provideDBHelper(dbHelper: AppDbHelper): DBHelper {
        return dbHelper
    }

    @Provides
    @Singleton
    fun provideDataManager(preferencesHelper: PreferenceHelper, dbHelper: DBHelper): DataManager {
        return DataManager(preferencesHelper, dbHelper)
    }
}