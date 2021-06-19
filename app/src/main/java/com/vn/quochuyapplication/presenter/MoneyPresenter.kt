package com.vn.quochuyapplication.presenter

import com.vn.quochuyapplication.base.RxPresenter
import com.vn.quochuyapplication.base.model.DataManager
import com.vn.quochuyapplication.ui.money.IMoneyView
import javax.inject.Inject

class MoneyPresenter @Inject constructor(var dataManager: DataManager): RxPresenter<IMoneyView>() {

}