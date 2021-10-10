package com.vn.quochuyapplication.data.model

import android.os.Parcelable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
open class Customer : RealmObject(), Parcelable {
    companion object {
        const val CUSTOMER_ID_FIELD = "id"
    }

    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var gender: Int = -1
    lateinit var name: String
    lateinit var address: String
    lateinit var dob: String
    lateinit var phoneNumber: String
    lateinit var leftDiop: String
    lateinit var rightDiop: String
    lateinit var glassesType: String
    lateinit var frameType: String
    lateinit var amount: String

}