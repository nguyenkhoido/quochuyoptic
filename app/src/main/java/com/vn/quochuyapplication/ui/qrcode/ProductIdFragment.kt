package com.vn.quochuyapplication.ui.qrcode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vn.quochuyapplication.base.BaseFragment
import com.vn.quochuyapplication.presenter.ProductIdPresenter

class ProductIdFragment : BaseFragment<ProductIdPresenter>(), IProductIdView {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initInject() {
        fragmentComponent().inject(this)
    }

    override fun initViews() {

    }

    override fun initDataAndEvents() {
    }
}