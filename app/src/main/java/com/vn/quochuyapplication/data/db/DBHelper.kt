package com.vn.quochuyapplication.data.db

import com.vn.quochuyapplication.data.model.*
import io.reactivex.Flowable
import io.realm.RealmResults
import java.sql.Timestamp
import java.util.concurrent.Flow

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
    fun updateProductByCode(productId: String?, productName: String, productCode: String, productPrice: Int, productQuantity: Int, productCategory: String, onDone: Runnable?, onFail: Runnable?)
    fun updateQuantityProductByCode(productCode: String, productQuantity: Int, productCategory: String)
    fun deleteProduct(iProduct: IProduct)

    //Money
    fun saveSellItem(
        sellItemList: ArrayList<SellItem>?,
        onDone: Runnable?,
        onFail: Runnable?
    )

    fun getSellItem(): Flowable<RealmResults<SellItem>>

    fun getSellItemWithFilter(startTime: Long, endTime: Long): Flowable<RealmResults<SellItem>>
    //Customer
    fun saveCustomer(customer: Customer, onDone: Runnable?, onFail: Runnable?)
    fun updateCustomer(
        id: String,
        gender: Int,
        customerName: String,
        customerPhone: String,
        address: String,
        dob: String,
        leftDiop: String,
        rightDiop: String,
        glassType: String,
        frameType: String,
        amount: String, onDone: Runnable?, onFail: Runnable?
    )

    fun deleteCustomer(customer: Customer?, onDone: Runnable?, onFail: Runnable?)
    fun getCustomer(customerId: String?): Customer
    fun getCustomerList(): MutableList<Customer>?

    fun saveProductId(id: ProductId, onDone: Runnable?, onFail: Runnable?)
    fun getAllProductId(): MutableList<ProductId>?
    fun getProductIdByCode(id: String, onDone: Runnable?, onFail: Runnable?): ProductId?
    fun deleteAllProductId(onDone: Runnable?, onFail: Runnable?)

    fun <T : IProduct?> saveFrame(t: T)
    fun <T : IProduct?> saveGlasses(t: T)
    fun <T : IProduct?> saveLense(t: T)
    fun <T : IProduct?> saveOtherProduct(t: T)
}