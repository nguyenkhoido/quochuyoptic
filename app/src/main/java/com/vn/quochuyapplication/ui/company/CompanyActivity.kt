package com.vn.quochuyapplication.ui.company

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavInflater
import androidx.navigation.fragment.NavHostFragment
import com.vn.quochuyapplication.R
import com.vn.quochuyapplication.base.BaseActivity
import com.vn.quochuyapplication.constant.AppConstants
import com.vn.quochuyapplication.databinding.ActivityCompanyBinding
import com.vn.quochuyapplication.eventbus.AddProductEvent
import com.vn.quochuyapplication.presenter.CompanyPresenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class CompanyActivity : BaseActivity<CompanyPresenter>(), ICompanyInteracted.ICompanyView, View.OnClickListener {
    lateinit var companyBinding: ActivityCompanyBinding
    private lateinit var navController: NavController
    private lateinit var graph: NavGraph
    private lateinit var inflater: NavInflater

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
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_product) as NavHostFragment
        val data = Bundle().apply {
            putString(AppConstants.COMPANY_NAME, intent.getStringExtra(AppConstants.COMPANY_NAME))
        }
        navController = navHostFragment.navController
        inflater = navController.navInflater
        graph = inflater.inflate(R.navigation.navigation_product)
        navController.setGraph(graph, data)
    }

    override fun initDataAndEvents() {
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
            /*AddProductEvent.ADD_PRODUCT -> {
                if (mProductList.isNotEmpty()) {
                    mProductList.add(event.product!!)
                    mProductAdapter.update(mProductList)
                }
            }*/
        }
    }

    /*override fun onGetProductSuccess(productList: MutableList<IProduct>) {

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
    }*/

    override fun onStop() {
        super.onStop()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    override fun onClick(v: View?) {

    }
}