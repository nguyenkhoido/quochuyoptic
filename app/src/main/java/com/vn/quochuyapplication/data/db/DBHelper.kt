package com.vn.quochuyapplication.data.db

import com.vn.quochuyapplication.data.model.*
import io.reactivex.Flowable
import io.realm.RealmResults

interface DBHelper {
    //Company
    fun saveCompany(company: Company?)
    fun getCompanyList(): MutableList<Company>?

    //Product
    fun getFrameList(companyName: String?): MutableList<Frame>?
    fun getGlassesList(companyName: String?): MutableList<Glasses>?
    fun getLenseList(companyName: String?): MutableList<Lense>?
    fun getOtherProductList(companyName: String?): MutableList<Other>?
    fun getProductByCode(productCode: String?, productCategory: String?): IProduct?
    fun updateProductByCode(productName: String, productCode: String, productPrice: Int, productQuantity: Int, productCategory: String)
    fun updateQuantityProductByCode(productCode: String, productQuantity: Int, productCategory: String)
    fun deleteProduct(iProduct: IProduct)

    //Money
    fun saveSellItem(
        sellItemList: ArrayList<SellItem>?,
        onDone: Runnable?,
        onFail: Runnable?
    )

    fun getSellItem(): Flowable<RealmResults<SellItem>>

    //Customer
    fun saveCustomer(customer: Customer)
    fun getCustomer(customerId: String?): Customer
    fun getCustomerList(): MutableList<Customer>?

    fun <T : IProduct?> saveFrame(t: T)
    fun <T : IProduct?> saveGlasses(t: T)
    fun <T : IProduct?> saveLense(t: T)
    fun <T : IProduct?> saveOtherProduct(t: T)
}