package com.vn.quochuyapplication.ui.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vn.quochuyapplication.adapter.CustomerAdapter
import com.vn.quochuyapplication.base.BaseFragment
import com.vn.quochuyapplication.customview.MySearchView
import com.vn.quochuyapplication.data.model.Customer
import com.vn.quochuyapplication.databinding.FragmentCustomersBinding
import com.vn.quochuyapplication.eventbus.AddCustomerEvent
import com.vn.quochuyapplication.presenter.CustomerPresenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class CustomerFragment : BaseFragment<CustomerPresenter>(), ICustomerView,
    CustomerAdapter.ItemCustomerClick {
    private var _customerBinding: FragmentCustomersBinding? = null
    private var mCustomerAdapter: CustomerAdapter? = null

    companion object {
        fun newInstance(): CustomerFragment {
            return CustomerFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _customerBinding = FragmentCustomersBinding.inflate(inflater, container, false)
        mRootView = _customerBinding?.root!!
        return mRootView
    }

    override fun initInject() {
        fragmentComponent().inject(this)
    }

    override fun initViews() {
        _customerBinding?.floatAddCustomer?.setOnClickListener {
            DialogAddCustomer.newInstance()
                .show(activity?.supportFragmentManager!!, DialogAddCustomer::class.java.simpleName)
        }
    }

    override fun initDataAndEvents() {
        presenter.mAllCustomerList = ArrayList()
        presenter.dataManager.getCustomerList()?.let { presenter.mAllCustomerList?.addAll(it) }
        if (null != presenter.mAllCustomerList && presenter.mAllCustomerList?.size!! > 0) {
            val lnManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            mCustomerAdapter =
                CustomerAdapter(requireContext(), presenter.mAllCustomerList!!, this).also {
                    _customerBinding?.recycleListCustomer?.layoutManager = lnManager
                    _customerBinding?.recycleListCustomer?.adapter = it

                    _customerBinding?.searchView?.setOnSearchKeyChangeListener(object :
                        MySearchView.OnSearchKeyChangeListener {
                        override fun onSearchKeyChanged(key: String) {
                            loadList(presenter.filterList(key.trim()), key.isNotEmpty())
                        }

                    })
                }

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onCustomerAdd(event: AddCustomerEvent) {
        when (event.event) {
            AddCustomerEvent.ADD_CUSTOMER -> {
                if (presenter.mAllCustomerList?.isNotEmpty() == true) {
                    presenter.mAllCustomerList?.add(event.customer!!)
                    mCustomerAdapter?.update(presenter.mAllCustomerList)
                }
            }
        }
    }

    override fun onItemClick(customer: Customer?) {
        val customerObj = presenter.getCustomer(customerId = customer?.id ?: "")
        DialogAddCustomer.newInstance(customerObj)
            .show(activity?.supportFragmentManager!!, DialogAddCustomer::class.java.simpleName)
    }

    private fun loadList(list: ArrayList<Customer>?, isFilter: Boolean = false) {
        activity?.let {
            if (!it.isFinishing && !it.isDestroyed) {
                (_customerBinding?.recycleListCustomer?.adapter as? CustomerAdapter)?.update(
                    list
                )

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

}