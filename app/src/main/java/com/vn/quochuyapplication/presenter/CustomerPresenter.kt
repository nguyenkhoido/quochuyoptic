package com.vn.quochuyapplication.presenter

import com.vn.quochuyapplication.base.RxPresenter
import com.vn.quochuyapplication.base.model.DataManager
import com.vn.quochuyapplication.data.model.Customer
import com.vn.quochuyapplication.ui.customer.ICustomerView
import javax.inject.Inject

class CustomerPresenter @Inject constructor(var dataManager: DataManager) :
    RxPresenter<ICustomerView>() {
    var mAllCustomerList: ArrayList<Customer?>? = null

    fun getCustomer(customerId: String): Customer {
        return dataManager.getCustomer(customerId)
    }

    /**
     * Clear highlight
     *
     */


    fun filterList(key: String): ArrayList<Customer>? {
        var res: ArrayList<Customer>? = null
        mAllCustomerList?.let { list ->
            var isContain: Boolean
            ArrayList<Customer>().run {
                list.forEach { item ->
                    isContain = false
                    if (item?.name?.contains(key, true) == true) {
                        isContain = true
                    }
                    if (item?.phoneNumber?.contains(key, true) == true) {
                        isContain = true
                    }
                    if (isContain) item?.let { add(it) }
                }
                res = this
            }
        }
        return res
    }

    fun handleData(listItem: ArrayList<Customer>?): ArrayList<Customer>? {
        listItem?.let { list ->
            if (list.isNotEmpty()) {
                val listRes = ArrayList<Customer>()
                var lastItem: Customer? = null
                list.forEach { item ->
                    if (lastItem == null || (item.name != lastItem?.name)) {
                        listRes.add(item)
                    }
                    listRes.add(item)
                    lastItem = item
                }
                return listRes
            }
        }
        return listItem
    }

}