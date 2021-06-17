package com.vn.quochuyapplication.ui.customer

import com.vn.quochuyapplication.base.RxPresenter
import com.vn.quochuyapplication.base.model.DataManager
import javax.inject.Inject

class CustomerPresenter @Inject constructor(var dataManager: DataManager): RxPresenter<ICustomerView>() {


}