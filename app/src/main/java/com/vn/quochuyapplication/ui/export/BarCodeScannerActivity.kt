package com.vn.quochuyapplication.ui.export

import android.os.Bundle
import com.google.zxing.Result
import com.vn.quochuyapplication.base.SimpleActivity
import com.vn.quochuyapplication.databinding.ActivityBarCodeScannerBinding

class BarCodeScannerActivity : SimpleActivity(){

    private lateinit var barCodeScannerBinding: ActivityBarCodeScannerBinding
    override fun initViews() {

    }

    override fun initDataAndEvents() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        barCodeScannerBinding = ActivityBarCodeScannerBinding.inflate(layoutInflater)
        setContentView(barCodeScannerBinding.root)

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

   /* override fun handleResult(rawResult: Result?) {

        println("Prints scan results:   " + rawResult?.text)
        println("Prints the scan format (qrcode, pdf417 etc.):    " + rawResult?.barcodeFormat.toString())
        // If you would like to resume scanning, call this method below:

    }*/
}