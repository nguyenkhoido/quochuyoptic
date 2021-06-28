package com.vn.quochuyapplication.ui.money

import com.vn.quochuyapplication.base.BaseView
import com.vn.quochuyapplication.data.model.SellItem
import io.realm.RealmResults

interface IMoneyView : BaseView {
    fun onLoadSellListSuccess(sellList: MutableList<SellItem>?)
    fun onLoadSellListEmpty()
    fun onLoadSellListFailed()
}