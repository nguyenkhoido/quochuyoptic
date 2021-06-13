package com.vn.quochuyapplication.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class SimpleActivity : AppCompatActivity() {
    protected abstract fun getLayoutId(): Int
    protected abstract fun initViews()
    protected abstract fun initDataAndEvents()
    protected open fun onViewCreated() {}
    private var doubleBackToExit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        onViewCreated()
        initViews()
        initDataAndEvents()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    fun showToast(mess: String?) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show()
    }

    fun showToast(messRes: Int) {
        Toast.makeText(this, messRes, Toast.LENGTH_SHORT).show()
    }

}