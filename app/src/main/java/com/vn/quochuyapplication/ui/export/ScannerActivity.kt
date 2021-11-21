package com.vn.quochuyapplication.ui.export

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.zxing.Result
import com.vn.quochuyapplication.R
import com.vn.quochuyapplication.databinding.ActivityScannerBinding
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScannerActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {
    private var scannerActivityBinding: ActivityScannerBinding? = null
    private var mScannerView: ZXingScannerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scannerActivityBinding = ActivityScannerBinding.inflate(layoutInflater)
        setContentView(scannerActivityBinding?.root)

        mScannerView = ZXingScannerView(this)
        scannerActivityBinding?.scannerView?.addView(mScannerView)
    }

    override fun onResume() {
        super.onResume()
        mScannerView?.setResultHandler(this)
        mScannerView?.startCamera()
    }

    override fun onPause() {
        super.onPause()
        mScannerView?.stopCamera()
    }

    override fun handleResult(rawResult: Result?) {
        println("Prints scan results    " + rawResult?.text)
        println("Prints the scan format   " + rawResult?.barcodeFormat.toString())
        val intent = Intent()
        intent.putExtra("REQUEST_CODE", "SCAN_QRCODE")
        intent.putExtra("SCAN_RESULT", rawResult?.text)
        setResult(Activity.RESULT_OK, intent)
        mScannerView?.stopCamera()
        finishAfterTransition()

        //mScannerView?.resumeCameraPreview(this);
    }
}