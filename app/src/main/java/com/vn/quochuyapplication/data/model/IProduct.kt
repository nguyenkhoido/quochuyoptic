package com.vn.quochuyapplication.data.model

import android.os.Parcelable

interface IProduct : Parcelable {
    fun productCategory(): String?
    fun productName(): String?
    fun productCompanyName(): String?
    fun productPrice(): Int
    fun productCode(): String?
    fun productQuantity(): Int
}