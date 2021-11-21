package com.vn.quochuyapplication.presenter

import com.vn.quochuyapplication.base.RxPresenter
import com.vn.quochuyapplication.base.model.DataManager
import com.vn.quochuyapplication.data.model.ProductId
import com.vn.quochuyapplication.ui.qrcode.IProductIdView
import javax.inject.Inject

class ProductIdPresenter @Inject constructor(var dataManager: DataManager) : RxPresenter<IProductIdView>() {
    fun getProductCodeList(): MutableList<ProductId>? {
        return dataManager.getAllProductId()
    }
}