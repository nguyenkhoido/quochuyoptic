package com.vn.quochuyapplication.ui.company.product

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.vn.quochuyapplication.R
import com.vn.quochuyapplication.adapter.ProductAdapter
import com.vn.quochuyapplication.base.BaseFragment
import com.vn.quochuyapplication.data.model.Frame
import com.vn.quochuyapplication.data.model.IProduct
import com.vn.quochuyapplication.databinding.ActivityCompanyBinding
import com.vn.quochuyapplication.databinding.FragmentFrameBinding
import com.vn.quochuyapplication.presenter.FrameProductPresenter
import com.vn.quochuyapplication.ui.DialogAddProduct

class FragmentFrame : BaseFragment<FrameProductPresenter>(), IFrameProductView, IProductListener, ProductAdapter.ItemProductClick, View.OnClickListener, DialogInterface.OnDismissListener {
    private val args: FragmentFrameArgs by navArgs()
    private var _frameFragBinding: FragmentFrameBinding? = null
    lateinit var mProductAdapter: ProductAdapter
    lateinit var mProductList: ArrayList<IProduct>
    private lateinit var mCompanyName: String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _frameFragBinding = FragmentFrameBinding.inflate(inflater, container, false)
        mRootView = _frameFragBinding?.root!!
        return mRootView
    }

    override fun initInject() {
        fragmentComponent().inject(this)
    }

    override fun initViews() {
        _frameFragBinding?.floatAddProduct?.setOnClickListener(this)
    }

    override fun initDataAndEvents() {
        mCompanyName = args.companyName
        val frameList: MutableList<Frame> = presenter.dataManager.getFrameList(mCompanyName)
        mProductList = ArrayList()
        mProductList.addAll(frameList)
        mProductAdapter = ProductAdapter(requireContext(), mProductList, this).also {
            val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            _frameFragBinding?.recycleProductList?.adapter = it
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            _frameFragBinding?.floatAddProduct -> {
                activity?.supportFragmentManager?.let {
                    val dialogAddProduct = DialogAddProduct.newInstance(mCompanyName, "Gọng Kính")
                    dialogAddProduct.setProductListener(this)
                    dialogAddProduct.show(it, DialogAddProduct::class.java.simpleName)
                }
            }
        }
    }

    override fun onItemClick(iProduct: IProduct) {
        activity?.supportFragmentManager?.let { DialogAddProduct.newInstance(iProduct).show(it, DialogAddProduct::class.java.simpleName) }
    }

    override fun onItemLongClick(iProduct: IProduct) {
        MaterialAlertDialogBuilder(requireContext(), R.style.MaterialAlertDialog_MaterialComponents)
            .setIcon(android.R.drawable.stat_sys_warning)
            .setTitle("Xóa Sản Phẩm")
            .setMessage("Bạn có chắc muốn xóa sản phẩm này?")
            .setOnDismissListener(this)
            .setPositiveButton("Xóa") { dialog, which ->
                presenter.dataManager.deleteProduct(iProduct)
                dialog.dismiss()
            }.setNegativeButton("Hủy") { dialog, which ->
                dialog.dismiss()
            }.show()

    }

    override fun onDismiss(dialog: DialogInterface?) {

    }

    override fun onAddProductSuccess(product: IProduct) {
        mProductList.add(product)
        mProductAdapter.refreshData(mProductList)
    }
}