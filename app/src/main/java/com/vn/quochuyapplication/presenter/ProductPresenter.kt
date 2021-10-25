package com.vn.quochuyapplication.presenter

import com.vn.quochuyapplication.base.RxPresenter
import com.vn.quochuyapplication.base.model.DataManager
import com.vn.quochuyapplication.ui.company.product.IProductView
import javax.inject.Inject

class ProductPresenter @Inject constructor(dataManager: DataManager) : RxPresenter<IProductView>() {
}