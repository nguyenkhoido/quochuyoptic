package com.vn.quochuyapplication.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@RealmClass
open class Other : RealmObject(), IProduct {
    companion object {
        const val PRODUCT_ID_FIELD = "productId"
    }
    var productId = UUID.randomUUID().toString()
    var category: String? = null
    var companyName: String? = null
    var productCode: String? = null
    var productName: String? = null
    var price = 0
    var quantity = 0

    override fun productCategory(): String? {
        return this.category
    }

    override fun productName(): String? {
        return this.productName
    }

    override fun productCompanyName(): String? {
        return this.companyName
    }

    override fun productPrice(): Int {
        return this.price
    }

    override fun productCode(): String? {
        return this.productCode
    }

    override fun productQuantity(): Int {
        return this.quantity
    }
}