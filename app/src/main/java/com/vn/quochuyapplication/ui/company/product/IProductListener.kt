package com.vn.quochuyapplication.ui.company.product

import com.vn.quochuyapplication.data.model.IProduct

interface IProductListener {
    fun onAddProductSuccess(product: IProduct)
}