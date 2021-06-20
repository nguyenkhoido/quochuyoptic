package com.vn.quochuyapplication.ui.company

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.vn.quochuyapplication.R
import com.vn.quochuyapplication.adapter.ProductAdapter
import com.vn.quochuyapplication.base.BaseActivity
import com.vn.quochuyapplication.constant.AppConstants
import com.vn.quochuyapplication.data.model.*
import com.vn.quochuyapplication.databinding.ActivityCompanyBinding
import com.vn.quochuyapplication.eventbus.AddProductEvent
import com.vn.quochuyapplication.presenter.CompanyPresenter
import com.vn.quochuyapplication.ui.DialogAddProduct
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class CompanyActivity : BaseActivity<CompanyPresenter>(), ProductAdapter.ItemProductClick, ICompanyInteracted.ICompanyView, View.OnClickListener {
    private lateinit var mCompanyName: String
    lateinit var companyBinding: ActivityCompanyBinding
    lateinit var mProductAdapter: ProductAdapter
    lateinit var mProductList: ArrayList<IProduct>

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
        companyBinding.floatAddProduct.setOnClickListener(this)
    }

    override fun initDataAndEvents() {
        mCompanyName = intent.getStringExtra(AppConstants.COMPANY_NAME) ?: ""
        val frameList: MutableList<Frame> = mPresenter.dataManager.getFrameList(mCompanyName)
        val glassesList: MutableList<Glasses> = mPresenter.dataManager.getGlassesList(mCompanyName)
        val lenseList: MutableList<Lense> = mPresenter.dataManager.getLenseList(mCompanyName)
        val otherList: MutableList<Other> = mPresenter.dataManager.getOtherProductList(mCompanyName)
        mProductList = ArrayList()
        mProductList.addAll(frameList)
        mProductList.addAll(glassesList)
        mProductList.addAll(lenseList)
        mProductList.addAll(otherList)

        mProductAdapter = ProductAdapter(this, mProductList, this).also {
            val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            companyBinding.recycleProductList.layoutManager = layoutManager
            companyBinding.recycleProductList.adapter = it
        }

    }

    override fun onStart() {
        super.onStart()
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onProductAdd(event: AddProductEvent) {
        when (event.event) {
            AddProductEvent.ADD_PRODUCT -> {
                if (mProductList.isNotEmpty()) {
                    mProductList.add(event.product!!)
                    mProductAdapter.update(mProductList)
                }
            }
        }
    }

    override fun onGetProductSuccess(productList: MutableList<IProduct>) {

    }

    override fun onGetProductFailed() {
    }

    override fun onItemClick(iProduct: IProduct) {
        DialogAddProduct.newInstance(iProduct).show(supportFragmentManager, DialogAddProduct::class.java.simpleName)
    }

    override fun onItemLongClick(iProduct: IProduct) {
        MaterialAlertDialogBuilder(this, R.style.MaterialAlertDialog_MaterialComponents)
            .setIcon(android.R.drawable.stat_sys_warning)
            .setTitle("Xóa Sản Phẩm")
            .setMessage("Bạn có chắc muốn xóa sản phẩm này?")
            .setPositiveButton("Xóa") { dialog, which ->
                mPresenter.dataManager.deleteProduct(iProduct)
                dialog.dismiss()
            }.setNegativeButton("Hủy") { dialog, which ->
                dialog.dismiss()
            }.show()

    }

    override fun onClick(v: View?) {
        when (v) {
            companyBinding.floatAddProduct -> {
                DialogAddProduct.newInstance(mCompanyName).show(supportFragmentManager, DialogAddProduct::class.java.simpleName)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }
}