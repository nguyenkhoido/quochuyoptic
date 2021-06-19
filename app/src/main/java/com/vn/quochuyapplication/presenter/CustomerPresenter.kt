package com.vn.quochuyapplication.presenter

import com.vn.quochuyapplication.base.RxPresenter
import com.vn.quochuyapplication.base.model.DataManager
import com.vn.quochuyapplication.ui.customer.ICustomerView
import javax.inject.Inject

class CustomerPresenter @Inject constructor(var dataManager: DataManager): RxPresenter<ICustomerView>() {


}