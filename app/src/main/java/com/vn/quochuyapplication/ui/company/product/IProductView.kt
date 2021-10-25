package com.vn.quochuyapplication.ui.company.product

import com.vn.quochuyapplication.base.BaseView
import com.vn.quochuyapplication.data.model.IProduct

interface IProductView : BaseView {
    fun onGetProductSuccess(productList: MutableList<IProduct>)
    fun onGetProductFailed()
}