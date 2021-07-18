package com.vn.quochuyapplication.ui.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vn.quochuyapplication.adapter.CustomerAdapter
import com.vn.quochuyapplication.base.BaseFragment
import com.vn.quochuyapplication.data.model.Customer
import com.vn.quochuyapplication.databinding.DialogAddCustomerBinding
import com.vn.quochuyapplication.databinding.FragmentCustomersBinding
import com.vn.quochuyapplication.presenter.CustomerPresenter


class CustomerFragment : BaseFragment<CustomerPresenter>(), ICustomerView, CustomerAdapter.ItemCustomerClick {
    private var _customerBinding: FragmentCustomersBinding? = null
    var customerAdapter: CustomerAdapter? = null
    var customerList: ArrayList<Customer?>? = null

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
        _customerBinding?.floatAddCustomer?.setOnClickListener {
            DialogAddCustomer.newInstance().show(activity?.supportFragmentManager!!, DialogAddCustomer::class.java.simpleName)
        }
    }

    override fun initDataAndEvents() {
        customerList = ArrayList()
        presenter.dataManager.getCustomerList()?.let { customerList?.addAll(it) }
        if (null != customerList && customerList?.size!! > 0) {
            val lnManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            customerAdapter = CustomerAdapter(requireContext(), customerList!!, this).also {
                _customerBinding?.recycleListCustomer?.layoutManager = lnManager
                _customerBinding?.recycleListCustomer?.adapter = it
            }
        }
    }

    override fun onItemClick(customer: Customer?) {
        val customerObj = presenter.getCustomer(customerId = customer?.id ?: "")
        DialogAddCustomer.newInstance(customerObj).show(activity?.supportFragmentManager!!, DialogAddCustomer::class.java.simpleName)
    }

}