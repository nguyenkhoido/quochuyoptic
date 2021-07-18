package com.vn.quochuyapplication.presenter

import com.vn.quochuyapplication.base.RxPresenter
import com.vn.quochuyapplication.base.model.DataManager
import com.vn.quochuyapplication.data.model.Customer
import com.vn.quochuyapplication.ui.customer.ICustomerView
import javax.inject.Inject

class CustomerPresenter @Inject constructor(var dataManager: DataManager) : RxPresenter<ICustomerView>() {

    fun getCustomerList() {

    }

    fun getCustomer(customerId: String): Customer {
       return dataManager.getCustomer(customerId)
    }

}