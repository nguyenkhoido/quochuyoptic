package com.vn.quochuyapplication.base.model

import com.vn.quochuyapplication.data.db.DBHelper
import com.vn.quochuyapplication.data.model.*
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

    override fun getProductByCode(productId: String?, companyName: String?): IProduct {
        return dbHelper.getProductByCode(productId, companyName)!!
    }

    override fun updateProductByCode(iProduct: IProduct) {
        dbHelper.updateProductByCode(iProduct)
    }

    override fun deleteProduct(iProduct: IProduct) {
        dbHelper.deleteProduct(iProduct)
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