package com.vn.quochuyapplication.ui.input

import com.vn.quochuyapplication.R
import com.vn.quochuyapplication.base.BaseFragment

class ImportFragment: BaseFragment<ImportPresenter>(), IImportView {

    companion object {
        fun newInstance(): ImportFragment {
            return ImportFragment()
        }
    }
    override fun initViews() {
    }

    override fun initDataAndEvents() {
    }

    override fun getLayoutResId() = R.layout.fragment_import

    override fun initInject() {
        fragmentComponent().inject(this)
    }
}
