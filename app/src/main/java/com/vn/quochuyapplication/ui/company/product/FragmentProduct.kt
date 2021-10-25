package com.vn.quochuyapplication.ui.company.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.vn.quochuyapplication.base.BaseFragment
import com.vn.quochuyapplication.constant.AppConstants
import com.vn.quochuyapplication.data.model.IProduct
import com.vn.quochuyapplication.databinding.FragmentFrameBinding
import com.vn.quochuyapplication.databinding.FragmentProductBinding
import com.vn.quochuyapplication.presenter.ProductPresenter

class FragmentProduct : BaseFragment<ProductPresenter>(), IProductView {

    private var _productBinding: FragmentProductBinding? = null
    private var mCompanyName: String? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _productBinding = FragmentProductBinding.inflate(inflater, container, false)
        mRootView = _productBinding?.root!!
        return mRootView
    }

    override fun initInject() {
        fragmentComponent().inject(this)
    }

    override fun initViews() {
        _productBinding?.cardGK?.setOnClickListener {
            val action = FragmentProductDirections.actionFragmentProductToFragmentFrame()
            action.companyName = mCompanyName.toString()
            findNavController().navigate(action)
        }

        _productBinding?.cardTK?.setOnClickListener {
            val action = FragmentProductDirections.actionFragmentProductToFragmentGlass()
            action.companyName = mCompanyName.toString()
            findNavController().navigate(action)
        }

    }

    override fun initDataAndEvents() {
        mCompanyName = requireArguments().getString(AppConstants.COMPANY_NAME)
    }

    override fun onGetProductSuccess(productList: MutableList<IProduct>) {

    }

    override fun onGetProductFailed() {

    }
}