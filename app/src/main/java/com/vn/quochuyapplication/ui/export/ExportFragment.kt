package com.vn.quochuyapplication.ui.export

import com.vn.quochuyapplication.R
import com.vn.quochuyapplication.base.BaseFragment
import com.vn.quochuyapplication.ui.customer.CustomerFragment
import javax.inject.Inject

class ExportFragment: BaseFragment<ExportPresenter>(), IExportView {
    companion object {
        fun newInstance(): ExportFragment {
            return ExportFragment()
        }
    }
    override fun initViews() {
    }

    override fun initDataAndEvents() {
    }

    override fun getLayoutResId() = R.layout.fragment_export

    override fun initInject() {
        fragmentComponent().inject(this)
    }
}