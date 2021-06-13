package com.vn.quochuyapplication.base.model

import com.vn.quochuyapplication.constant.Category
import com.vn.quochuyapplication.data.db.DBHelper
import com.vn.quochuyapplication.data.model.*
import io.realm.Realm
import io.realm.RealmResults
import javax.inject.Inject

class AppDbHelper @Inject constructor(private val realmLocalDB: Realm) : DBHelper {
    override fun saveCompany(company: Company?) {
        realmLocalDB.executeTransaction {
            it.insertOrUpdate(company!!)
        }
    }

    override fun getCompanyList(): RealmResults<Company> {
        return realmLocalDB.where(Company::class.java).findAll()
    }


    override fun <T : IProduct?> saveLense(product: T) {
        realmLocalDB.executeTransaction {
            it.insertOrUpdate(product as Lense)
        }
    }

    override fun getLenseList(companyName: String?): MutableList<Lense> {
        return realmLocalDB.where(Lense::class.java).equalTo("companyName", companyName).findAll()
    }

    override fun <T : IProduct?> saveFrame(product: T) {
        realmLocalDB.executeTransaction {
            it.insertOrUpdate(product as Frame)
        }
    }

    override fun getFrameList(companyName: String?): MutableList<Frame> {
        return realmLocalDB.where(Frame::class.java).equalTo("companyName", companyName).findAll()
    }

    override fun <T : IProduct?> saveGlasses(product: T) {
        realmLocalDB.executeTransaction {
            it.insertOrUpdate(product as Glasses)
        }
    }

    override fun getGlassesList(companyName: String?): MutableList<Glasses> {
        return realmLocalDB.where(Glasses::class.java).equalTo("companyName", companyName).findAll()
    }

    override fun <T : IProduct?> saveOtherProduct(product: T) {
        realmLocalDB.executeTransaction {
            it.insertOrUpdate(product as Other)
        }
    }

    override fun getOtherProductList(companyName: String?): MutableList<Other> {
        return realmLocalDB.where(Other::class.java).equalTo("companyName", companyName).findAll()
    }

    override fun getProductByCode(productCode: String?, productCategory: String?): IProduct {
        return when (productCategory) {
            Category.GONG_KINH -> {
                realmLocalDB.where(Frame::class.java).equalTo("productCode", productCode).findFirst()!!
            }
            Category.LENSE -> {
                realmLocalDB.where(Lense::class.java).equalTo("productCode", productCode).findFirst()!!
            }
            Category.TRONG_KINH -> {
                realmLocalDB.where(Glasses::class.java).equalTo("productCode", productCode).findFirst()!!
            }
            else -> {
                realmLocalDB.where(Other::class.java).equalTo("productCode", productCode).findFirst()!!
            }
        }
    }


}