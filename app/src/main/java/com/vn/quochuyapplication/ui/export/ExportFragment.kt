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
import com.vn.quochuyapplication.R
import com.vn.quochuyapplication.base.BaseFragment
import com.vn.quochuyapplication.data.model.IProduct
import com.vn.quochuyapplication.databinding.FragmentExportBinding
import com.vn.quochuyapplication.presenter.ExportPresenter

class ExportFragment : BaseFragment<ExportPresenter>(), AdapterView.OnItemSelectedListener, IExportView {

    private var _exportBinding: FragmentExportBinding? = null
    private var mIProduct: IProduct? = null

    companion object {
        fun newInstance(): ExportFragment {
            return ExportFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _exportBinding = FragmentExportBinding.inflate(inflater, container, false)
        mRootView = _exportBinding?.root!!
        return mRootView
    }

    override fun initViews() {
        setUpCategorySpinner()
        _exportBinding?.buttonScanQrcode?.setOnClickListener {
            try {
                val intent = Intent("com.google.zxing.client.android.SCAN")
                intent.putExtra("SCAN_MODE", "QR_CODE_MODE") // "PRODUCT_MODE for bar codes
                startActivityForResult(intent, 79)
            } catch (e: Exception) {
                val marketUri: Uri = Uri.parse("market://details?id=com.google.zxing.client.android")
                val marketIntent = Intent(Intent.ACTION_VIEW, marketUri)
                startActivity(marketIntent)
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
        }
    }

}