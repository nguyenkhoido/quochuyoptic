package com.vn.quochuyapplication.base.di.module

import com.vn.quochuyapplication.data.db.RealmConfig
import dagger.Module
import dagger.Provides
import io.realm.Realm
import javax.inject.Singleton

@Module
class DBModule {

    @Provides
    @Singleton
    fun provideDBHelper(): Realm {
        return RealmConfig.instance?.getRealm()!!
    }
}