package com.vn.quochuyapplication.presenter

import com.vn.quochuyapplication.base.RxPresenter
import com.vn.quochuyapplication.base.model.DataManager
import com.vn.quochuyapplication.data.model.IProduct
import com.vn.quochuyapplication.data.model.SellItem
import com.vn.quochuyapplication.ui.money.IMoneyView
import com.vn.quochuyapplication.utils.DateTimeUtil
import com.vn.quochuyapplication.utils.RxUtils
import javax.inject.Inject

class MoneyPresenter @Inject constructor(var dataManager: DataManager) : RxPresenter<IMoneyView>() {

    /*fun loadSellItem(startTime: Long, endTime: Long) {
        addSubscribe(dataManager.getSellItemWithFilter(startTime, endTime).compose(RxUtils.rxSchedulerHelper()).subscribe({
            if (it != null && it.size > 0) {
                mView?.onLoadSellListSuccess(it)
            } else {
                mView?.onLoadSellListEmpty()
            }
        }, {
            mView?.onLoadSellListFailed()
        }))
    }*/

    fun loadSellItemWithFilter(startTime: Long, endTime: Long) {
        addSubscribe(dataManager.getSellItemWithFilter(startTime, endTime).compose(RxUtils.rxSchedulerHelper()).subscribe({
            if (!it.isNullOrEmpty()) {
                mView?.onLoadSellListSuccess(it)
            } else {
                mView?.onLoadSellListEmpty()
            }
        }, {
            mView?.onLoadSellListFailed()
        }))
    }

    fun saveSellItemIntoDB(sellItem: ArrayList<SellItem>?) {
        dataManager.saveSellItem(sellItem, {

        }, {

        })
    }


}