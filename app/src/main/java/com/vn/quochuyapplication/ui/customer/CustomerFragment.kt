package com.vn.quochuyapplication.ui.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vn.quochuyapplication.R
import com.vn.quochuyapplication.base.BaseFragment
import com.vn.quochuyapplication.databinding.FragmentCustomersBinding
import com.vn.quochuyapplication.presenter.CustomerPresenter


class CustomerFragment : BaseFragment<CustomerPresenter>(), ICustomerView {
    private var _customerBinding: FragmentCustomersBinding? = null

    companion object {
        fun newInstance(): CustomerFragment {
            return CustomerFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _customerBinding = FragmentCustomersBinding.inflate(inflater, container, false)
        mRootView = _customerBinding?.root!!
        return mRootView
    }


    override fun initInject() {
        fragmentComponent().inject(this)
    }

    override fun initViews() {

    }

    override fun initDataAndEvents() {
    }

}