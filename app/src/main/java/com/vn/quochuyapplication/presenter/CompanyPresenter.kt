package com.vn.quochuyapplication.presenter

import com.vn.quochuyapplication.base.RxPresenter
import com.vn.quochuyapplication.base.model.DataManager
import com.vn.quochuyapplication.data.model.IProduct
import com.vn.quochuyapplication.ui.company.ICompanyInteracted
import javax.inject.Inject

class CompanyPresenter @Inject constructor(var dataManager: DataManager) : RxPresenter<ICompanyInteracted.ICompanyView>(), ICompanyInteracted.ICompanyPresenter {
    override fun saveProduct(companyName: String, product: IProduct) {
    }

    override fun getProduct(companyName: String) {

    }

}