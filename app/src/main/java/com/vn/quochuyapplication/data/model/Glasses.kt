package com.vn.quochuyapplication.data.model

import io.realm.RealmObject
import io.realm.annotations.RealmClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@RealmClass
open class Glasses : RealmObject(), IProduct {
    var category: String? = null
    var companyName: String? = null
    var productCode: String? = null
    var price = 0
    var quantity = 0

    override fun category(): String? {
        return this.category
    }

    override fun companyName(): String? {
        return this.companyName
    }

    override fun price(): Int {
        return this.price
    }

    override fun productCode(): String? {
        return this.productCode
    }

    override fun quantity(): Int {
        return this.quantity
    }
}