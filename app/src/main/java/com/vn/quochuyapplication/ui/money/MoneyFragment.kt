package com.vn.quochuyapplication.ui.money

import com.vn.quochuyapplication.R
import com.vn.quochuyapplication.base.BaseFragment

class MoneyFragment: BaseFragment<MoneyPresenter>() {

    companion object {
        fun newInstance(): MoneyFragment {
            return MoneyFragment()
        }
    }

    override fun initViews() {
    }

    override fun initDataAndEvents() {
    }

    override fun getLayoutResId() = R.layout.fragment_money

    override fun initInject() {
        fragmentComponent().inject(this)
    }
}