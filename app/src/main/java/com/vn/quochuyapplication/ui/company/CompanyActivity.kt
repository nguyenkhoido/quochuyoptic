package com.vn.quochuyapplication.ui.company

import android.app.Activity
import android.content.Intent
import com.vn.quochuyapplication.base.BaseActivity
import com.vn.quochuyapplication.constant.AppConstants
import com.vn.quochuyapplication.data.model.IProduct
import com.vn.quochuyapplication.databinding.ActivityCompanyBinding
import com.vn.quochuyapplication.presenter.CompanyPresenter

class CompanyActivity : BaseActivity<CompanyPresenter>(), ICompanyInteracted.ICompanyView {
    lateinit var companyBinding: ActivityCompanyBinding

    companion object {
        fun starter(context: Activity, companyName: String) {
            context.startActivity(Intent(context, CompanyActivity::class.java).putExtra(AppConstants.COMPANY_NAME, companyName))
        }
    }

    override fun initInject() {
        activityComponent.inject(this)
    }

    override fun initViews() {
        companyBinding = ActivityCompanyBinding.inflate(layoutInflater)
        setContentView(companyBinding.root)
    }

    override fun initDataAndEvents() {
        val companyName = intent.getStringExtra(AppConstants.COMPANY_NAME)

    }

    override fun onGetProductSuccess(productList: MutableList<IProduct>) {

    }

    override fun onGetProductFailed() {
    }
}