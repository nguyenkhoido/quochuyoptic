package com.vn.quochuyapplication.ui.export

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vn.quochuyapplication.base.BaseFragment
import com.vn.quochuyapplication.databinding.FragmentExportBinding
import com.vn.quochuyapplication.databinding.FragmentImportBinding
import com.vn.quochuyapplication.presenter.ExportPresenter

class ExportFragment : BaseFragment<ExportPresenter>(), IExportView {

    private var _exportBinding: FragmentExportBinding? = null

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
    }

    override fun initDataAndEvents() {
    }

    override fun initInject() {
        fragmentComponent().inject(this)
    }
}