package com.vn.quochuyapplication.presenter

import com.vn.quochuyapplication.base.RxPresenter
import com.vn.quochuyapplication.base.model.DataManager
import com.vn.quochuyapplication.ui.company.product.IFrameProductView
import javax.inject.Inject

class FrameProductPresenter @Inject constructor(var dataManager: DataManager) : RxPresenter<IFrameProductView>() {
}