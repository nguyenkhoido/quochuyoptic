package com.vn.quochuyapplication.presenter

import com.vn.quochuyapplication.base.RxPresenter
import com.vn.quochuyapplication.base.model.DataManager
import com.vn.quochuyapplication.ui.qrcode.IProductIdView
import javax.inject.Inject

class ProductIdPresenter @Inject constructor(dataManager: DataManager): RxPresenter<IProductIdView>() {
}