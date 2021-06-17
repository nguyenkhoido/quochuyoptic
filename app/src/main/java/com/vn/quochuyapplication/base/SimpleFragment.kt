package com.vn.quochuyapplication.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


abstract class SimpleFragment : Fragment() {
    @JvmField
    protected var mContext: Context? = null
    lateinit var mRootView: View

    protected abstract fun initViews()
    protected abstract fun initDataAndEvents()
    protected abstract fun getLayoutResId(): Int

    override fun onAttach(context: Context) {
        mContext = context
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater.inflate(getLayoutResId(), container, false)
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }



}