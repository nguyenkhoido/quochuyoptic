package com.vn.quochuyapplication.ui.customer

import android.os.Bundle
import com.vn.quochuyapplication.R
import com.vn.quochuyapplication.base.BaseFragment


class CustomerFragment: BaseFragment<CustomerPresenter>(), ICustomerView {

    companion object {
        fun newInstance(): CustomerFragment {
            return CustomerFragment()
        }
    }

    override fun initInject() {
        fragmentComponent().inject(this)
    }

    override fun initViews() {

    }

    override fun initDataAndEvents() {
    }

    override fun getLayoutResId() = R.layout.fragment_customers

}