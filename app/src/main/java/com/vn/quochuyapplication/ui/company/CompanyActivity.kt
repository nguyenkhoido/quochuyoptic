package com.vn.quochuyapplication.ui.company

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vn.quochuyapplication.R
import com.vn.quochuyapplication.adapter.ProductAdapter
import com.vn.quochuyapplication.constant.AppConstants

class CompanyActivity : AppCompatActivity() {

    companion object {
        fun starter(context: Activity, companyName: String) {
            context.startActivity(Intent(context, CompanyActivity::class.java).putExtra(AppConstants.COMPANY_NAME, companyName))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company)
        val companyName = intent.getStringExtra(AppConstants.COMPANY_NAME)

    }
}