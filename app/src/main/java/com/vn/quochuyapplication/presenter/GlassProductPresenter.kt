package com.vn.quochuyapplication.presenter

import com.vn.quochuyapplication.base.RxPresenter
import com.vn.quochuyapplication.base.model.DataManager
import com.vn.quochuyapplication.ui.company.product.IGlassProductView
import javax.inject.Inject

class GlassProductPresenter @Inject constructor(var dataManager: DataManager) : RxPresenter<IGlassProductView>(){
}