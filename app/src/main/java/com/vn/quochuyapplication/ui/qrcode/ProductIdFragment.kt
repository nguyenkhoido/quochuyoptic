package com.vn.quochuyapplication.ui.qrcode

import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.vn.quochuyapplication.adapter.ProductQRCodeAdapter
import com.vn.quochuyapplication.base.BaseFragment
import com.vn.quochuyapplication.data.model.ProductId
import com.vn.quochuyapplication.databinding.FragmentProductIdBinding
import com.vn.quochuyapplication.presenter.ProductIdPresenter
import java.util.*


class ProductIdFragment : BaseFragment<ProductIdPresenter>(), IProductIdView, ProductQRCodeAdapter.ItemProductClick {
    private var _productIdBinding: FragmentProductIdBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _productIdBinding = FragmentProductIdBinding.inflate(inflater, container, false)
        mRootView = _productIdBinding?.root!!
        return mRootView
    }

    override fun initInject() {
        fragmentComponent().inject(this)
    }


    override fun initDataAndEvents() {
    }

    override fun initViews() {
        ProductQRCodeAdapter(requireContext(), presenter.getProductCodeList()!!, this).also {
            _productIdBinding?.recycleProductCode?.adapter = it
        }
    }

    override fun onItemClick(iProduct: ProductId) {
        val clipboard = activity?.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?
        val clip = ClipData.newPlainText("Mã QR Code", iProduct.productCode)
        clipboard?.setPrimaryClip(clip)
        Toast.makeText(requireContext(), "Đã copy mã", Toast.LENGTH_SHORT).show()
    }

    override fun onItemLongClick(iProduct: ProductId) {

    }
}