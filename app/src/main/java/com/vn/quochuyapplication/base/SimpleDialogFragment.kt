package com.vn.quochuyapplication.base

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.RelativeLayout
import androidx.fragment.app.DialogFragment

open class SimpleDialogFragment : DialogFragment() {
    var _mActivity: Activity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _mActivity = activity
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val root = RelativeLayout(activity)
        root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        val dialog = Dialog(_mActivity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(root)
        if (dialog.window != null) {
            dialog.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        return dialog
    }

    fun onBackPressed() {
        if (_mActivity != null) _mActivity!!.onBackPressed()
    }
}