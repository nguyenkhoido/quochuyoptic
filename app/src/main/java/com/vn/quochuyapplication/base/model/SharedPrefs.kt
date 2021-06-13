package com.vn.quochuyapplication.base.model

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.reflect.TypeToken
import com.vn.quochuyapplication.QHApplication
import java.lang.reflect.Type


class SharedPrefs private constructor() {
    private val mSharedPreferences: SharedPreferences = QHApplication.getInstance()
        .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(key: String, anonymousClass: Class<T>): T {
        return when (anonymousClass) {
            String::class.java -> {
                mSharedPreferences.getString(key, "") as T
            }
            Boolean::class.java -> {
                java.lang.Boolean.valueOf(mSharedPreferences.getBoolean(key, false)) as T
            }
            Float::class.java -> {
                java.lang.Float.valueOf(mSharedPreferences.getFloat(key, 0f)) as T
            }
            Int::class.java -> {
                Integer.valueOf(mSharedPreferences.getInt(key, 0)) as T
            }
            Long::class.java -> {
                java.lang.Long.valueOf(mSharedPreferences.getLong(key, 0)) as T
            }
            else -> {
                QHApplication.getInstance().getGSon()
                    .fromJson(mSharedPreferences.getString(key, ""), anonymousClass)
            }
        }
    }

    fun <T> put(key: String, data: T) {
        val editor = mSharedPreferences.edit()
        when (data) {
            is String -> {
                editor.putString(key, data as String)
            }
            is Boolean -> {
                editor.putBoolean(key, data as Boolean)
            }
            is Float -> {
                editor.putFloat(key, data as Float)
            }
            is Int -> {
                editor.putInt(key, data as Int)
            }
            is Long -> {
                editor.putLong(key, data as Long)
            }
            else -> {
                editor.putString(key, QHApplication.getInstance().getGSon().toJson(data))
            }
        }
        editor.apply()
    }

    fun <T> getDataList(key: String): Set<T> {
        val type = object : TypeToken<Set<T>>() {}.type
        return QHApplication.getInstance().getGSon()
            .fromJson(mSharedPreferences.getString(key, ""), type)
    }

    fun <T> saveArrayList(list: ArrayList<T>, key: String) {
        val json: String = QHApplication.getInstance().getGSon().toJson(list)
        put(key, json)
    }

    fun <T> getArrayList(key: String): ArrayList<T> {

        val dataAsString = get(key, String::class.java)
        val type: Type =
            object : TypeToken<ArrayList<T>>() {}.type
        return QHApplication.getInstance().getGSon().fromJson(dataAsString, type)

    }

    fun putStringSet(key: String, data: Set<String>) {
        val editor = mSharedPreferences.edit()
        editor.putStringSet(key, data.toSet())
        editor.apply()
    }

    fun getStringSet(key: String) {
        mSharedPreferences.getStringSet(key, setOf<String>())
    }


    fun clear() {
        mSharedPreferences.edit().clear().apply()
    }

    companion object {
        private val PREFS_NAME = "LENDER_APP"
        private var mInstance: SharedPrefs? = null

        val instance: SharedPrefs
            get() {
                if (mInstance == null) {
                    mInstance = SharedPrefs()
                }
                return mInstance as SharedPrefs
            }
    }
}
