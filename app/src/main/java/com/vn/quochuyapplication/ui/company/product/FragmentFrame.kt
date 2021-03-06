package com.vn.quochuyapplication.ui.company.product

import android.content.DialogInterface
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
            _frameFragBinding?.recycleProductList?.adapter = it
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            _frameFragBinding?.floatAddProduct -> {
                activity?.supportFragmentManager?.let {
                    val dialogAddProduct = DialogAddProduct.newInstance(mCompanyName, Category.GONG_KINH)
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
            .setTitle("X??a S???n Ph???m")
            .setMessage("B???n c?? ch???c mu???n x??a s???n ph???m n??y?")
            .setOnDismissListener(this)
            .setPositiveButton("X??a") { dialog, which ->
                presenter.dataManager.deleteProduct(iProduct)
                dialog.dismiss()
            }.setNegativeButton("H???y") { dialog, which ->
                dialog.dismiss()
            }.show()

    }

    override fun onDismiss(dialog: DialogInterface?) {

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
        Toast.makeText(context, "L??u m?? qr code th??nh c??ng", Toast.LENGTH_SHORT).show()
    }
}