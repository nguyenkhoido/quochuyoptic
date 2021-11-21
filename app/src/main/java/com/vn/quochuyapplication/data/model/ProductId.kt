package com.vn.quochuyapplication.data.model

import io.realm.RealmObject

open class ProductId : RealmObject() {

    var productCode: String? = null
    var productCategory: String? = null
}