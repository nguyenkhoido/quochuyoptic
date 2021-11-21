package com.vn.quochuyapplication.presenter

import com.vn.quochuyapplication.base.RxPresenter
import com.vn.quochuyapplication.base.model.DataManager
import com.vn.quochuyapplication.constant.Category
import com.vn.quochuyapplication.data.model.Frame
import com.vn.quochuyapplication.data.model.IProduct
import com.vn.quochuyapplication.data.model.SellItem
import com.vn.quochuyapplication.ui.export.IExportView
import com.vn.quochuyapplication.utils.DateTimeUtil
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class ExportPresenter @Inject constructor(var dataManager: DataManager) : RxPresenter<IExportView>() {

    fun convertToSellItem(product: IProduct?): SellItem {
        val sellItem = SellItem()
        sellItem.productCode = product?.productCode()
        sellItem.productPrice = product?.productPrice() ?: 0
        sellItem.sellDate = DateTimeUtil.getTimeStamp()
        sellItem.sellDateAsTimeStamp = Calendar.getInstance().timeInMillis
        return sellItem
    }

    fun saveSellItemIntoDB(sellItem: ArrayList<SellItem>?) {
        dataManager.saveSellItem(sellItem, {
            mView?.onSaveLocalSuccess()
        }, {
            mView?.onSaveLocalFailed()
        })
    }

    fun updateProductQuantity(productCode: String, quantity: Int, productCategory: String) {
        dataManager.updateQuantityProductByCode(productCode, quantity, productCategory)
    }

}