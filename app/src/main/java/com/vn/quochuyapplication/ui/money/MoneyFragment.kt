package com.vn.quochuyapplication.ui.money

import android.os.Bundle
import android.view.*
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vn.quochuyapplication.R
import com.vn.quochuyapplication.adapter.MoneyAdapter
import com.vn.quochuyapplication.base.BaseFragment
import com.vn.quochuyapplication.data.model.MonthEnum
import com.vn.quochuyapplication.data.model.SellItem
import com.vn.quochuyapplication.databinding.FragmentMoneyBinding
import com.vn.quochuyapplication.presenter.MoneyPresenter
import com.vn.quochuyapplication.utils.StringUtils
import java.util.*

class MoneyFragment : BaseFragment<MoneyPresenter>(), IMoneyView, MoneyAdapter.ItemMoneyClick {
    private var _moneyBinding: FragmentMoneyBinding? = null
    private var mArraySellItem: MutableList<SellItem>? = null
    private var mMoneyAdapter: MoneyAdapter? = null
    private var mSellItem: SellItem? = null
    private var mCurrentMonth: String? = null
    private var mFrom: Long? = null
    private var mTo: Long? = null

    companion object {
        fun newInstance(): MoneyFragment {
            return MoneyFragment()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _moneyBinding = FragmentMoneyBinding.inflate(inflater, container, false)
        mRootView = _moneyBinding?.root!!
        setHasOptionsMenu(true)
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
    }

    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.money_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.one) {
            return true
        }
        *//*return NavigationUI.onNavDestinationSelected(item!!,
            view!!.findNavController())
                || super.onOptionsItemSelected(item)*//*
        return true
    }*/


    override fun initDataAndEvents() {
        val start = Calendar.getInstance()
        start.get(Calendar.MONTH)
        start.set(Calendar.DAY_OF_MONTH, 1)
        start.set(Calendar.HOUR_OF_DAY, 0)
        start.set(Calendar.MINUTE, 0)
        start.set(Calendar.SECOND, 0)

        val end = Calendar.getInstance()
        end.get(Calendar.MONTH)
        end.set(Calendar.DAY_OF_MONTH, end.getActualMaximum(Calendar.DAY_OF_MONTH))
        end.set(Calendar.HOUR_OF_DAY, 23)
        end.set(Calendar.MINUTE, 59)
        end.set(Calendar.SECOND, 59)

        println("Now of the month:       " + start.time);
        println("... in milliseconds:      " + start.timeInMillis);

        println("End of the month:       " + end.time);
        println("... in milliseconds:      " + end.timeInMillis);
        mArraySellItem = ArrayList()

    }

    override fun initViews() {
        setUpMonthSpinner()
    }

    override fun initInject() {
        fragmentComponent().inject(this)
    }

    private fun initRecycleData(sellItemList: MutableList<SellItem>) {
        _moneyBinding?.recycleListMoney?.visibility = View.VISIBLE
        _moneyBinding?.recycleListMoney.let { recyclerView ->
            mMoneyAdapter = MoneyAdapter(requireContext(), sellItemList, this).also { adapter ->
                val layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                recyclerView?.layoutManager = layoutManager
                recyclerView?.adapter = adapter
            }
        }
    }

    override fun onLoadSellListSuccess(sellList: MutableList<SellItem>?) {
        try {
            requireContext().let {
                mArraySellItem?.clear()
                mArraySellItem?.addAll(sellList!!)
                if (null != mMoneyAdapter) {
                    mMoneyAdapter?.update(mArraySellItem)
                } else {
                    mArraySellItem?.let { it1 -> initRecycleData(it1) }
                }

                var sumValue = 0
                mArraySellItem?.forEach {
                    sumValue += it.productPrice
                }
                _moneyBinding?.textMoney?.text = String.format(getString(R.string.format_vnd), StringUtils.customFormatVND(sumValue.toDouble()))
            }
            _moneyBinding?.recycleListMoney?.visibility = View.VISIBLE
        } catch (ex: Exception) {

        }
    }

    override fun onLoadSellListEmpty() {
        _moneyBinding?.recycleListMoney?.visibility = View.GONE
        _moneyBinding?.textMoney?.text = ""
        Toast.makeText(requireContext(), "Không tìm thấy đữ liệu", Toast.LENGTH_SHORT).show()
    }

    override fun onLoadSellListFailed() {
        _moneyBinding?.recycleListMoney?.visibility = View.GONE
        _moneyBinding?.textMoney?.text = ""
        Toast.makeText(requireContext(), "Đã xảy ra lỗi", Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(sellItem: SellItem?) {

    }

    private fun setUpMonthSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.months_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            _moneyBinding?.spinnerMonths?.adapter = adapter
            _moneyBinding?.spinnerMonths?.setSelection(Calendar.getInstance().get(Calendar.MONTH))
            _moneyBinding?.spinnerMonths?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    when (parent?.getItemAtPosition(position).toString()) {
                        MonthEnum.JAN.month -> {
                            setCalendar(Calendar.JANUARY)
                        }
                        MonthEnum.FEB.month -> {
                            setCalendar(Calendar.FEBRUARY)
                        }
                        MonthEnum.MARCH.month -> {
                            setCalendar(Calendar.MARCH)
                        }
                        MonthEnum.APR.month -> {
                            setCalendar(Calendar.APRIL)
                        }
                        MonthEnum.MAY.month -> {
                            setCalendar(Calendar.MAY)
                        }
                        MonthEnum.JUNE.month -> {
                            setCalendar(Calendar.JUNE)
                        }
                        MonthEnum.JULY.month -> {
                            setCalendar(Calendar.JULY)
                        }
                        MonthEnum.AUG.month -> {
                            setCalendar(Calendar.AUGUST)
                        }
                        MonthEnum.SEP.month -> {
                            setCalendar(Calendar.SEPTEMBER)
                        }
                        MonthEnum.OCT.month -> {
                            setCalendar(Calendar.OCTOBER)
                        }
                        MonthEnum.NOV.month -> {
                            setCalendar(Calendar.NOVEMBER)
                        }
                        MonthEnum.DEC.month -> {
                            setCalendar(Calendar.DECEMBER)
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
        }
    }

    private fun setCalendar(value: Int) {
        /*val mycal: Calendar = GregorianCalendar(2020, Calendar.FEBRUARY, 1)
        println("Days in FEB:       " + mycal.getActualMaximum(Calendar.DAY_OF_MONTH));*/
        val startCal: Calendar = Calendar.getInstance(Locale("vi-VN"))
        val endCal: Calendar = Calendar.getInstance(Locale("vi-VN"))

        startCal.set(Calendar.MONTH, value)
        startCal.set(Calendar.DAY_OF_MONTH, 1)
        startCal.set(Calendar.HOUR_OF_DAY, 0)
        startCal.set(Calendar.MINUTE, 0)
        startCal.set(Calendar.SECOND, 0)

        endCal.set(Calendar.MONTH, value)
        endCal.set(Calendar.DAY_OF_MONTH, endCal.getActualMaximum(Calendar.DAY_OF_MONTH))
        endCal.set(Calendar.HOUR_OF_DAY, 23)
        endCal.set(Calendar.MINUTE, 59)
        endCal.set(Calendar.SECOND, 59)
        println("Start of the month:       " + startCal.time);
        println("... in milliseconds:      " + startCal.timeInMillis);

        println("End of the month:       " + endCal.time);
        println("... in milliseconds:      " + endCal.timeInMillis);
        mFrom = startCal.timeInMillis
        mTo = endCal.timeInMillis

        presenter.loadSellItemWithFilter(mFrom ?: Calendar.getInstance().timeInMillis, mTo ?: Calendar.getInstance().timeInMillis)
    }


}