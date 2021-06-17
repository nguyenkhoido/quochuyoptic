package com.vn.quochuyapplication.ui.money

import com.vn.quochuyapplication.base.RxPresenter
import com.vn.quochuyapplication.base.model.DataManager
import javax.inject.Inject

class MoneyPresenter @Inject constructor(var dataManager: DataManager): RxPresenter<IMoneyView>() {

}