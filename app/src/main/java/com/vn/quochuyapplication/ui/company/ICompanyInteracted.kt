package com.vn.quochuyapplication.ui.company

import com.vn.quochuyapplication.base.BaseView
import com.vn.quochuyapplication.data.model.IProduct

interface ICompanyInteracted {

    interface ICompanyPresenter {
        fun saveProduct(companyName: String, product: IProduct)
        fun getProduct(companyName: String)
    }

    interface ICompanyView : BaseView {
        //fun onGetProductSuccess(productList: MutableList<IProduct>)
        //fun onGetProductFailed()
    }
}