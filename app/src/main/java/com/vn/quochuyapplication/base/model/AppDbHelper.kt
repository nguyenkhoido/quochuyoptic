package com.vn.quochuyapplication.base.model

import com.vn.quochuyapplication.constant.Category
import com.vn.quochuyapplication.data.db.DBHelper
import com.vn.quochuyapplication.data.model.*
import io.reactivex.Flowable
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults
import io.realm.Sort
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

    override fun getProductByCode(productCode: String?, productCategory: String?): IProduct? {
        return when (productCategory) {
            Category.GONG_KINH -> {
                realmLocalDB.where(Frame::class.java).equalTo("productCode", productCode).findFirst()
            }
            Category.LENSE -> {
                realmLocalDB.where(Lense::class.java).equalTo("productCode", productCode).findFirst()
            }
            Category.TRONG_KINH -> {
                realmLocalDB.where(Glasses::class.java).equalTo("productCode", productCode).findFirst()
            }
            else -> {
                realmLocalDB.where(Other::class.java).equalTo("productCode", productCode).findFirst()
            }
        }
    }

    override fun updateProductByCode(
        productId: String?,
        productName: String,
        productCode: String,
        productPrice: Int,
        productQuantity: Int,
        productCategory: String,
        onDone: Runnable?,
        onFail: Runnable?
    ) {
        realmLocalDB.executeTransactionAsync({
            when (productCategory) {
                Category.GONG_KINH -> {
                    val frame = it.where(Frame::class.java).equalTo(Frame.PRODUCT_ID_FIELD, productId).findFirst()
                    frame?.productName = productName
                    frame?.productCode = productCode
                    frame?.quantity = productQuantity
                    frame?.price = productPrice
                    frame?.category = productCategory
                }
                /*  Category.LENSE -> {
                      val lense = it.where(Lense::class.java).findFirst()
                      lense?.productName = productName
                      lense?.productCode = productCode
                      lense?.quantity = productQuantity
                      lense?.price = productPrice
                      lense?.category = productCategory
                  }*/
                Category.TRONG_KINH -> {
                    val glasses = it.where(Glasses::class.java).equalTo(Glasses.PRODUCT_ID_FIELD, productId).findFirst()
                    glasses?.productName = productName
                    glasses?.productCode = productCode
                    glasses?.quantity = productQuantity
                    glasses?.price = productPrice
                    glasses?.category = productCategory
                }
                /*else -> {
                    val other = it.where(Other::class.java).findFirst()
                    other?.productName = productName
                    other?.productCode = productCode
                    other?.quantity = productQuantity
                    other?.price = productPrice
                    other?.category = productCategory
                }*/
            }

        }, { onDone?.run() }, { onFail?.run() })
    }

    override fun updateQuantityProductByCode(productCode: String, productQuantity: Int, productCategory: String) {
        realmLocalDB.executeTransaction {
            when (productCategory) {
                Category.GONG_KINH -> {
                    val frame = it.where(Frame::class.java).equalTo("productCode", productCode).findFirst()
                    frame?.quantity = productQuantity
                }
                Category.LENSE -> {
                    val lense = it.where(Lense::class.java).equalTo("productCode", productCode).findFirst()
                    lense?.quantity = productQuantity
                }
                Category.TRONG_KINH -> {
                    val glasses = it.where(Glasses::class.java).equalTo("productCode", productCode).findFirst()
                    glasses?.quantity = productQuantity

                }
                else -> {
                    val other = it.where(Other::class.java).equalTo("productCode", productCode).findFirst()
                    other?.quantity = productQuantity
                }
            }
        }
    }

    override fun deleteProduct(iProduct: IProduct) {
        realmLocalDB.executeTransaction {
            when (iProduct.productCategory()) {
                Category.GONG_KINH -> {
                    val result = it.where(Frame::class.java).equalTo(Frame.PRODUCT_ID_FIELD, (iProduct as Frame).productId)?.findFirst()
                    result?.deleteFromRealm()
                }
                Category.LENSE -> {
                    val result = it.where(Lense::class.java).equalTo(Lense.PRODUCT_ID_FIELD, (iProduct as Lense).productId)?.findFirst()
                    result?.deleteFromRealm()
                }
                Category.TRONG_KINH -> {
                    val result = it.where(Glasses::class.java).equalTo(Glasses.PRODUCT_ID_FIELD, (iProduct as Glasses).productId)?.findFirst()
                    result?.deleteFromRealm()
                }
                else -> {
                    val result = it.where(Other::class.java).equalTo(Other.PRODUCT_ID_FIELD, (iProduct as Other).productId)?.findFirst()
                    result?.deleteFromRealm()
                }
            }

        }
    }

    override fun saveSellItem(sellItemList: ArrayList<SellItem>?, onDone: Runnable?, onFail: Runnable?) {
        sellItemList?.let { list ->
            val realmList = RealmList<SellItem>()
            list.forEach {
                realmList.add(it)
            }

            realmLocalDB.executeTransactionAsync({
                it.insertOrUpdate(list)
            }, { onDone?.run() }, { onFail?.run() })
        }
    }

    override fun getSellItem(): Flowable<RealmResults<SellItem>> {
        return realmLocalDB.where(SellItem::class.java).sort("sellDateAsTimeStamp", Sort.DESCENDING).findAll().asFlowable()
    }

    override fun getSellItemWithFilter(startTime: Long, endTime: Long): Flowable<RealmResults<SellItem>> {
        return realmLocalDB.where(SellItem::class.java).between("sellDateAsTimeStamp", startTime, endTime).findAll().asFlowable()
    }

    override fun saveCustomer(customer: Customer, onDone: Runnable?, onFail: Runnable?) {
        realmLocalDB.executeTransactionAsync({
            it.insertOrUpdate(customer)
        }, { onDone?.run() }, { onFail?.run() })
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
        realmLocalDB.executeTransactionAsync({
            val customer = it.where(Customer::class.java).equalTo(Customer.CUSTOMER_ID_FIELD, id).findFirst()
            customer?.gender = gender
            customer?.name = customerName
            customer?.phoneNumber = customerPhone
            customer?.dob = dob
            customer?.address = address
            customer?.leftDiop = leftDiop
            customer?.rightDiop = rightDiop
            customer?.glassesType = glassType
            customer?.frameType = frameType
            customer?.amount = amount
        }, { onDone?.run() }, { onFail?.run() })
    }

    override fun deleteCustomer(customer: Customer?, onDone: Runnable?, onFail: Runnable?) {
        realmLocalDB.executeTransactionAsync({
            val customerInDB = it.where(Customer::class.java).equalTo(Customer.CUSTOMER_ID_FIELD, customer?.id)?.findFirst()
            customerInDB?.deleteFromRealm()
        }, { onDone?.run() }, {
            onFail?.run()
        })
    }

    override fun getCustomer(customerId: String?): Customer {
        return realmLocalDB.where(Customer::class.java).equalTo(Customer.CUSTOMER_ID_FIELD, customerId).findFirst()!!

    }

    override fun getCustomerList(): MutableList<Customer>? {
        return realmLocalDB.where(Customer::class.java).findAll()
    }

    override fun saveProductId(id: ProductId, onDone: Runnable?, onFail: Runnable?) {
        realmLocalDB.executeTransactionAsync({
            it.insertOrUpdate(id)
        }, { onDone?.run() }, {
            onFail?.run()
        })
    }

    override fun getAllProductId(): MutableList<ProductId>? {
        return realmLocalDB.where(ProductId::class.java).findAll()
    }

    override fun deleteAllProductId(onDone: Runnable?, onFail: Runnable?) {
        realmLocalDB.executeTransaction {
            val result = it.where(ProductId::class.java).findAll()
            result.deleteAllFromRealm()
        }
    }

}