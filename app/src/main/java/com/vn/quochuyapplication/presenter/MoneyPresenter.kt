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

    fun loadSellItem() {
        addSubscribe(dataManager.getSellItem().compose(RxUtils.rxSchedulerHelper()).subscribe({
            if (it != null && it.size > 0) {
                mView?.onLoadSellListSuccess(it)
            } else {
                mView?.onLoadSellListEmpty()
            }
        }, {
            mView?.onLoadSellListFailed()
        }))
    }

    fun convertToSellItem(product: IProduct?): SellItem {
        val sellItem = SellItem()
        sellItem.productCode = product?.productCode()
        sellItem.productPrice = product?.productPrice() ?: 0
        sellItem.sellDate = DateTimeUtil.getTimeStamp()
        return sellItem
    }

    fun saveSellItemIntoDB(sellItem: ArrayList<SellItem>?) {
        dataManager.saveSellItem(sellItem, {

        }, {

        })
    }
}