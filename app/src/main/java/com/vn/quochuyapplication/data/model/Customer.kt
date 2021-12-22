package com.vn.quochuyapplication.data.model

import android.os.Parcelable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@RealmClass
open class Customer : RealmObject(), Parcelable {
    companion object {
        const val CUSTOMER_ID_FIELD = "customerId"
    }

    var customerId: String = UUID.randomUUID().toString()
    var gender: Int = -1
    var name: String? = null
    var address: String? = null
    var dob: String? = null
    var phoneNumber: String? = null
    var leftDiop: String? = null
    var rightDiop: String? = null
    var glassesType: String? = null
    var frameType: String? = null
    var amount: String? = null

}