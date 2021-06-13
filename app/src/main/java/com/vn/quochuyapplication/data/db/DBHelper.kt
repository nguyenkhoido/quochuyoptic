package com.vn.quochuyapplication.data.db

import com.vn.quochuyapplication.data.model.*

interface DBHelper {
    //Company
    fun saveCompany(company: Company?)
    fun getCompanyList(): MutableList<Company>?
    //Product
    fun getFrameList(companyName: String?): MutableList<Frame>?
    fun getGlassesList(companyName: String?): MutableList<Glasses>?
    fun getLenseList(companyName: String?): MutableList<Lense>?
    fun getOtherProductList(companyName: String?): MutableList<Other>?
    fun getProductByCode(productId: String?, companyName: String?): IProduct?

    fun <T : IProduct?> saveFrame(t: T)
    fun <T : IProduct?> saveGlasses(t: T)
    fun <T : IProduct?> saveLense(t: T)
    fun <T : IProduct?> saveOtherProduct(t: T)
}