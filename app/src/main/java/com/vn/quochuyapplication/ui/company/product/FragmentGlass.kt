package com.vn.quochuyapplication.ui.company.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.vn.quochuyapplication.R
import com.vn.quochuyapplication.adapter.ProductAdapter
import com.vn.quochuyapplication.base.BaseFragment
import com.vn.quochuyapplication.constant.Category
import com.vn.quochuyapplication.data.model.Glasses
import com.vn.quochuyapplication.data.model.IProduct
import com.vn.quochuyapplication.databinding.FragmentGlassesBinding
import com.vn.quochuyapplication.presenter.GlassProductPresenter
import com.vn.quochuyapplication.ui.DialogAddProduct

class FragmentGlass : BaseFragment<GlassProductPresenter>(), IGlassProductView, ProductAdapter.ItemProductClick, IProductListener, View.OnClickListener {
    private val args: FragmentGlassArgs by navArgs()
    private var _glassFragBinding: FragmentGlassesBinding? = null
    lateinit var mProductAdapter: ProductAdapter
    lateinit var mProductList: ArrayList<IProduct>
    private lateinit var mCompanyName: String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _glassFragBinding = FragmentGlassesBinding.inflate(inflater, container, false)
        mRootView = _glassFragBinding?.root!!
        return mRootView
    }

    override fun initInject() {
        fragmentComponent().inject(this)
    }

    override fun initViews() {
        _glassFragBinding?.floatAddProduct?.setOnClickListener(this)
    }

    override fun initDataAndEvents() {
        mCompanyName = args.companyName
        //val frameList: MutableList<Frame> = mPresenter.dataManager.getFrameList(mCompanyName)
        val glassesList: MutableList<Glasses> = presenter.dataManager.getGlassesList(mCompanyName)
        // val lenseList: MutableList<Lense> = mPresenter.dataManager.getLenseList(mCompanyName)
        //val otherList: MutableList<Other> = mPresenter.dataManager.getOtherProductList(mCompanyName)
        mProductList = ArrayList()
        //mProductList.addAll(frameList)
        mProductList.addAll(glassesList)
        //mProductList.addAll(lenseList)
        //mProductList.addAll(otherList)

        mProductAdapter = ProductAdapter(requireContext(), mProductList, this).also {
            _glassFragBinding?.recycleProductList?.adapter = it
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            _glassFragBinding?.floatAddProduct -> {
                activity?.supportFragmentManager?.let {
                    val dialogAddProduct = DialogAddProduct.newInstance(mCompanyName, Category.TRONG_KINH)
                    dialogAddProduct.setProductListener(this)
                    dialogAddProduct.show(it, DialogAddProduct::class.java.simpleName)
                }
            }
        }
    }

    override fun onItemClick(iProduct: IProduct) {
        activity?.supportFragmentManager?.let {
            val dialogAddProduct = DialogAddProduct.newInstance(iProduct)
            dialogAddProduct.setProductListener(this)
            dialogAddProduct.show(it, DialogAddProduct::class.java.simpleName)
        }
    }

    override fun onItemLongClick(iProduct: IProduct) {
        MaterialAlertDialogBuilder(requireContext(), R.style.MaterialAlertDialog_MaterialComponents)
            .setIcon(android.R.drawable.stat_sys_warning)
            .setTitle("Xóa Sản Phẩm")
            .setMessage("Bạn có chắc muốn xóa sản phẩm này?")
            .setPositiveButton("Xóa") { dialog, which ->
                presenter.dataManager.deleteProduct(iProduct)
                dialog.dismiss()
            }.setNegativeButton("Hủy") { dialog, which ->
                dialog.dismiss()
            }.show()

    }

    override fun onAddProductSuccess(product: IProduct) {
        mProductList.add(product)
        mProductAdapter.update(mProductList)
    }

    override fun onUpdateProductSuccess(productId: String) {
        val updatedProduct = mProductList.find { it.productId() == productId }
        updatedProduct?.let { iProduct ->
            mProductAdapter.getPosition(iProduct).also { position ->
                mProductAdapter.notifyItemChanged(position)
            }
        }
    }

    override fun onSaveQrCodeContentSuccess(productQRCode: String) {
        Toast.makeText(context, "Lưu mã qr code thành công", Toast.LENGTH_SHORT).show()
    }


}