package com.vn.quochuyapplication.data.model

import io.realm.RealmObject

open class ProductId : RealmObject() {
    companion object {
        const val PRODUCT_ID_FIELD = "id"
    }
    lateinit var productQRCode: String
}