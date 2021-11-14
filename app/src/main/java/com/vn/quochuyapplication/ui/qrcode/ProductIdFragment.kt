package com.vn.quochuyapplication.ui.qrcode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vn.quochuyapplication.base.BaseFragment
import com.vn.quochuyapplication.databinding.FragmentMoneyBinding
import com.vn.quochuyapplication.databinding.FragmentProductIdBinding
import com.vn.quochuyapplication.presenter.ProductIdPresenter

class ProductIdFragment : BaseFragment<ProductIdPresenter>(), IProductIdView {
    private var _productIdBinding: FragmentProductIdBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _productIdBinding = FragmentProductIdBinding.inflate(inflater, container, false)
        mRootView = _productIdBinding?.root!!
        return mRootView
    }

    override fun initInject() {
        fragmentComponent().inject(this)
    }

    override fun initViews() {

    }

    override fun initDataAndEvents() {
    }
}