package com.vn.quochuyapplication.data.db

import com.vn.quochuyapplication.constant.AppConstants
import io.realm.Realm
import io.realm.RealmConfiguration
import java.security.SecureRandom
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RealmConfig {
    private var realm: Realm? = null

    fun getRealm(): Realm? {
        return realm
    }

    companion object {
        var instance: RealmConfig? = null
            get() {
                if (field == null) {
                    field = RealmConfig()
                }
                return field
            }
            private set
    }

    init {
        val config = RealmConfiguration.Builder()
            .name(AppConstants.REALM_DEFAULT_NAME)
            .schemaVersion(0)
            .allowQueriesOnUiThread(true)
            .allowWritesOnUiThread(true)
            .deleteRealmIfMigrationNeeded()
            .build()
        realm = try {
            Realm.getInstance(config)
        } catch (e: Exception) {
            Realm.deleteRealm(config)
            Realm.getInstance(config)
        }
    }
}