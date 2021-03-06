package com.vn.quochuyapplication.ui.export

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.zxing.integration.android.IntentIntegrator
import com.vn.quochuyapplication.QHApplication
import com.vn.quochuyapplication.R
import com.vn.quochuyapplication.base.BaseFragment
import com.vn.quochuyapplication.data.model.IProduct
import com.vn.quochuyapplication.data.model.ProductConvert
import com.vn.quochuyapplication.data.model.ProductId
import com.vn.quochuyapplication.data.model.SellItem
import com.vn.quochuyapplication.databinding.FragmentExportBinding
import com.vn.quochuyapplication.presenter.ExportPresenter
import com.vn.quochuyapplication.utils.StringUtils

class ExportFragment : BaseFragment<ExportPresenter>(), AdapterView.OnItemSelectedListener, IExportView {

    private var _exportBinding: FragmentExportBinding? = null
    private var mIProduct: IProduct? = null
    private var mArraySellItem: MutableList<SellItem>? = null

    companion object {
        fun newInstance(): ExportFragment {
            return ExportFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _exportBinding = FragmentExportBinding.inflate(inflater, container, false)
        mRootView = _exportBinding?.root!!
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
    }

    override fun initViews() {
        setUpCategorySpinner()
        _exportBinding?.buttonScanQrcode?.setOnClickListener {
            /* try {
                 val intent = Intent("com.google.zxing.client.android.SCAN")
                 intent.putExtra("SCAN_MODE", "QR_CODE_MODE") // "PRODUCT_MODE for bar codes
                 startActivityForResult(intent, 79)
             } catch (e: Exception) {
                 val marketUri: Uri = Uri.parse("market://details?id=com.google.zxing.client.android")
                 val marketIntent = Intent(Intent.ACTION_VIEW, marketUri)
                 startActivity(marketIntent)
             }*/
            val intent = Intent(context, ScannerActivity::class.java)
            qrScanResult.launch(intent)

        }
        _exportBinding?.buttonFind?.setOnClickListener {
            mIProduct = presenter.dataManager.getProductByCode(_exportBinding?.editFind?.text.toString(), _exportBinding?.spinnerCategory?.selectedItem.toString())

            if (null != mIProduct) {
                _exportBinding?.cardProductInfo?.visibility = View.VISIBLE
                _exportBinding?.textProductCompany?.text = "C??ng ty: ${mIProduct?.productCompanyName()}"
                _exportBinding?.textProductName?.text = "S???n ph???m: ${mIProduct?.productName()}"
                _exportBinding?.textProductPrice?.text = "Gi?? SP: ${StringUtils.customFormatVND(mIProduct?.productPrice()?.toDouble())}"
                _exportBinding?.textProductQuantity?.text = "S??? l?????ng: ${mIProduct?.productQuantity().toString()}"
            } else {
                Toast.makeText(requireContext(), R.string.str_empty_list, Toast.LENGTH_SHORT).show()
            }
        }
        _exportBinding?.buttonSell?.setOnClickListener {
            if (mIProduct?.productQuantity() == 0) {
                Toast.makeText(requireContext(), "Vui l??ng nh???p th??m s???n ph???m", Toast.LENGTH_SHORT).show()
            } else {
                if (mArraySellItem == null) {
                    mArraySellItem = ArrayList()
                }
                val sellItem = presenter.convertToSellItem(mIProduct)
                mArraySellItem!!.add(sellItem)
                _exportBinding?.cardProductInfo?.visibility = View.GONE
                presenter.saveSellItemIntoDB(mArraySellItem as ArrayList<SellItem>?)
            }
        }
    }

    override fun initDataAndEvents() {

    }

    override fun initInject() {
        fragmentComponent().inject(this)
    }

    private fun setUpCategorySpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.product_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            _exportBinding?.spinnerCategory?.adapter = adapter
            _exportBinding?.spinnerCategory?.onItemSelectedListener = this
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 79 && data != null) {
            val contents = data.getStringExtra("SCAN_RESULT")
            val productConvert = QHApplication.getInstance().getGSon().fromJson(contents, ProductConvert::class.java)

            if (null != productConvert) {
                _exportBinding?.cardProductInfo?.visibility = View.VISIBLE
            } else {
                Toast.makeText(requireContext(), R.string.str_empty_list, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSaveLocalSuccess() {
        presenter.updateProductQuantity(mIProduct?.productCode() ?: "", mIProduct?.productQuantity()?.minus(1)!!, mIProduct?.productCategory() ?: "")
        Toast.makeText(requireContext(), "L??u d??? li???u th??nh c??ng", Toast.LENGTH_SHORT).show()
    }

    override fun onSaveLocalFailed() {
        Toast.makeText(requireContext(), "L??u d??? li???u th???t b???i", Toast.LENGTH_SHORT).show()
    }

    private val qrScanResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        try {
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                if (intent?.hasExtra("REQUEST_CODE") == true) {
                    when (intent.getStringExtra("REQUEST_CODE")) {
                        "SCAN_QRCODE" -> {
                            val rawText = intent.getStringExtra("SCAN_RESULT")
                            Toast.makeText(context, rawText, Toast.LENGTH_LONG).show()
                            val qrCodeConvert = QHApplication.getInstance().getGSon().fromJson(rawText, ProductId::class.java)
                            mIProduct = presenter.dataManager.getProductByCode(qrCodeConvert.productCode, qrCodeConvert.productCategory)
                            if (mIProduct != null) {
                                _exportBinding?.cardProductInfo?.visibility = View.VISIBLE
                                _exportBinding?.textProductCompany?.text = "C??ng ty: ${mIProduct?.productCompanyName()}"
                                _exportBinding?.textProductName?.text = "S???n ph???m: ${mIProduct?.productName()}"
                                _exportBinding?.textProductPrice?.text = "Gi?? SP: ${StringUtils.customFormatVND(mIProduct?.productPrice()?.toDouble())}"
                                _exportBinding?.textProductQuantity?.text = "S??? l?????ng: ${mIProduct?.productQuantity().toString()}"
                            } else {
                                Toast.makeText(context, "Kh??ng t??m th???y s???n ph???m", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        } catch (ex: Exception) {

        }
    }
}