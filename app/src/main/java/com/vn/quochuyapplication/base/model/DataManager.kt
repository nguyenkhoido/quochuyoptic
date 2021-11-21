package com.vn.quochuyapplication.base.model

import com.vn.quochuyapplication.data.db.DBHelper
import com.vn.quochuyapplication.data.model.*
import io.reactivex.Flowable
import io.realm.RealmResults
import javax.inject.Inject

class DataManager @Inject constructor(
    private val preferenceHelper: PreferenceHelper,
    private val dbHelper: DBHelper
) : PreferenceHelper, DBHelper {

    override fun saveCompany(company: Company?) {
        dbHelper.saveCompany(company)
    }

    override fun getCompanyList(): MutableList<Company> {
        return dbHelper.getCompanyList()!!
    }

    override fun getFrameList(companyName: String?): MutableList<Frame> {
        return dbHelper.getFrameList(companyName)!!
    }

    override fun getGlassesList(companyName: String?): MutableList<Glasses> {
        return dbHelper.getGlassesList(companyName)!!
    }

    override fun getLenseList(companyName: String?): MutableList<Lense> {
        return dbHelper.getLenseList(companyName)!!
    }

    override fun getOtherProductList(companyName: String?): MutableList<Other> {
        return dbHelper.getOtherProductList(companyName)!!
    }

    override fun getProductByCode(productCode: String?, productCategory: String?): IProduct? {
        return dbHelper.getProductByCode(productCode, productCategory)
    }

    override fun updateProductByCode(
        productId: String?,
        productName: String,
        productCode: String,
        productPrice: Int,
        productQuantity: Int,
        productCategory: String,
        onDone: Runnable?, onFail: Runnable?
    ) {
        dbHelper.updateProductByCode(
            productId,
            productName,
            productCode,
            productPrice,
            productQuantity,
            productCategory,
            onDone,
            onFail
        )
    }

    override fun updateQuantityProductByCode(
        productCode: String,
        productQuantity: Int,
        productCategory: String
    ) {
        dbHelper.updateQuantityProductByCode(productCode, productQuantity, productCategory)
    }

    override fun deleteProduct(iProduct: IProduct) {
        dbHelper.deleteProduct(iProduct)
    }

    override fun saveSellItem(
        sellItemList: ArrayList<SellItem>?,
        onDone: Runnable?,
        onFail: Runnable?
    ) {
        dbHelper.saveSellItem(sellItemList, onDone, onFail)
    }

    override fun getSellItem(): Flowable<RealmResults<SellItem>> {
        return dbHelper.getSellItem()
    }

    override fun getSellItemWithFilter(startTime: Long, endTime: Long): Flowable<RealmResults<SellItem>> {
        return dbHelper.getSellItemWithFilter(startTime, endTime)
    }

    override fun saveCustomer(customer: Customer, onDone: Runnable?, onFail: Runnable?) {
        dbHelper.saveCustomer(customer, onDone, onFail)
    }

    override fun updateCustomer(
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
        amount: String,
        onDone: Runnable?, onFail: Runnable?
    ) {
        dbHelper.updateCustomer(id, gender, customerName, customerPhone, address, dob, leftDiop, rightDiop, glassType, frameType, amount, onDone, onFail)
    }

    override fun deleteCustomer(customer: Customer?, onDone: Runnable?, onFail: Runnable?) {
        dbHelper.deleteCustomer(customer, onDone, onFail)
    }

    override fun getCustomer(customerId: String?): Customer {
        return dbHelper.getCustomer(customerId)
    }

    override fun getCustomerList(): MutableList<Customer>? {
        return dbHelper.getCustomerList()
    }

    override fun saveProductId(id: ProductId, onDone: Runnable?, onFail: Runnable?) {
        dbHelper.saveProductId(id, onDone, onFail)
    }

    override fun getAllProductId(): MutableList<ProductId>? {
        return dbHelper.getAllProductId()
    }

    override fun deleteAllProductId(onDone: Runnable?, onFail: Runnable?) {
        dbHelper.deleteAllProductId(onDone, onFail)
    }

    override fun <T : IProduct?> saveFrame(t: T) {
        dbHelper.saveFrame(t)
    }

    override fun <T : IProduct?> saveGlasses(t: T) {
        dbHelper.saveGlasses(t)
    }

    override fun <T : IProduct?> saveLense(t: T) {
        dbHelper.saveLense(t)
    }

    override fun <T : IProduct?> saveOtherProduct(t: T) {
        dbHelper.saveOtherProduct(t)
    }

}