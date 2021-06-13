package com.vn.quochuyapplication.data.model

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class Money : RealmObject() {
    var productCode: String? = null
    var sellDate: String? = null
    var productPrice = 0
}