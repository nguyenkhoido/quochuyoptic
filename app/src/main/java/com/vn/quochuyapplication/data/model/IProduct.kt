package com.vn.quochuyapplication.data.model

import android.os.Parcelable

interface IProduct: Parcelable {
    fun category(): String?
    fun companyName(): String?
    fun price(): Int
    fun productCode(): String?
    fun quantity(): Int
}