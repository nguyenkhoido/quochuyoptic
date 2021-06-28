package com.vn.quochuyapplication.ui.money

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vn.quochuyapplication.adapter.MoneyAdapter
import com.vn.quochuyapplication.base.BaseFragment
import com.vn.quochuyapplication.constant.AppConstants
import com.vn.quochuyapplication.data.model.SellItem
import com.vn.quochuyapplication.databinding.FragmentMoneyBinding
import com.vn.quochuyapplication.presenter.MoneyPresenter
import com.vn.quochuyapplication.utils.StringUtils

class MoneyFragment : BaseFragment<MoneyPresenter>(), IMoneyView, MoneyAdapter.ItemMoneyClick {
    private var _moneyBinding: FragmentMoneyBinding? = null
    private var mArraySellItem: MutableList<SellItem>? = null
    private var mMoneyAdapter: MoneyAdapter? = null
    private var mSellItem: SellItem? = null

    companion object {
        fun newInstance(): MoneyFragment {
            return MoneyFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _moneyBinding = FragmentMoneyBinding.inflate(inflater, container, false)
        mRootView = _moneyBinding?.root!!
        return mRootView
    }

    override fun initViews() {
       // mSellItem = requireArguments().getParcelable(AppConstants.KEY_OBJ_PRODUCT)
    }

    override fun initDataAndEvents() {
        presenter.loadSellItem()
       /* if (null != mSellItem) {
            mArraySellItem = ArrayList()
            mArraySellItem?.add(mSellItem!!)
            presenter.saveSellItemIntoDB(mArraySellItem as ArrayList<SellItem>)
            initRecycleData(mArraySellItem as ArrayList<SellItem>)
        }*/
    }

    override fun initInject() {
        fragmentComponent().inject(this)
    }

    private fun initRecycleData(sellItemList: MutableList<SellItem>) {
        _moneyBinding?.recycleListMoney?.visibility = View.VISIBLE
        _moneyBinding?.recycleListMoney.let { recyclerView ->
            mMoneyAdapter = MoneyAdapter(requireContext(), sellItemList, this).also { adapter ->
                val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                recyclerView?.layoutManager = layoutManager
                recyclerView?.adapter = adapter
            }
        }
    }

    override fun onLoadSellListSuccess(sellList: MutableList<SellItem>?) {
        requireContext().let {
            mArraySellItem?.addAll(sellList!!)
            if (null != mMoneyAdapter) {
                mMoneyAdapter?.update(mArraySellItem)
            } else {
                mArraySellItem?.let { it1 -> initRecycleData(it1) }
            }
        }
    }

    override fun onLoadSellListEmpty() {
        _moneyBinding?.recycleListMoney?.visibility = View.GONE
    }

    override fun onLoadSellListFailed() {
        _moneyBinding?.recycleListMoney?.visibility = View.GONE
    }

    override fun onItemClick(sellItem: SellItem?) {
        var sumValue = 0
        mArraySellItem?.forEach {
            sumValue += it.productPrice
        }
        _moneyBinding?.textMoney?.text = StringUtils.customFormatVND(sumValue.toDouble())
    }

}