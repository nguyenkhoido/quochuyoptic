package com.vn.quochuyapplication.data.model

import android.os.Parcelable
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import kotlinx.android.parcel.Parcelize

@RealmClass
@Parcelize
open class SellItem : RealmObject(), Parcelable {
    var productCode: String? = null
    var sellDate: String? = null
    var productPrice = 0
}