package com.vn.quochuyapplication.ui.money

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vn.quochuyapplication.R
import com.vn.quochuyapplication.base.BaseFragment
import com.vn.quochuyapplication.databinding.FragmentMoneyBinding
import com.vn.quochuyapplication.presenter.MoneyPresenter

class MoneyFragment : BaseFragment<MoneyPresenter>() {
    private var _binding: FragmentMoneyBinding? = null

    companion object {
        fun newInstance(): MoneyFragment {
            return MoneyFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMoneyBinding.inflate(inflater, container, false)
        mRootView = _binding?.root!!
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