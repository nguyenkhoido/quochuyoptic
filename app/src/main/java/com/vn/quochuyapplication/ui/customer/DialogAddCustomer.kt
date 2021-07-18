package com.vn.quochuyapplication.ui.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import com.vn.quochuyapplication.QHApplication
import com.vn.quochuyapplication.R
import com.vn.quochuyapplication.adapter.CustomerAdapter
import com.vn.quochuyapplication.base.SimpleDialogFragment
import com.vn.quochuyapplication.base.model.DataManager
import com.vn.quochuyapplication.constant.AppConstants
import com.vn.quochuyapplication.data.model.Customer
import com.vn.quochuyapplication.data.model.IProduct
import com.vn.quochuyapplication.databinding.DialogAddCustomerBinding

class DialogAddCustomer : SimpleDialogFragment(), OnItemSelectedListener, View.OnClickListener {
    private var dialogAddCustomerBinding: DialogAddCustomerBinding? = null
    private var mCurrentGender: String? = null
    private var customerAdapter: CustomerAdapter? = null
    var customerList: ArrayList<Customer?>? = null

    companion object {
        fun newInstance(): DialogAddCustomer {
            return DialogAddCustomer()
        }

        fun newInstance(customer: Customer): DialogAddCustomer {
            val dialogAddCustomer = DialogAddCustomer()
            val args = Bundle()
            args.putParcelable(AppConstants.KEY_OBJ_CUSTOMER, customer)
            dialogAddCustomer.arguments = args
            return dialogAddCustomer
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialogAddCustomerBinding = DialogAddCustomerBinding.inflate(inflater, container, false)
        dialogAddCustomerBinding?.btnAddCustomer?.setOnClickListener(this)
        setUpCategorySpinner()
        return dialogAddCustomerBinding?.root
    }

    override fun initData() {
        val customerObj = requireArguments().getParcelable<Customer>(AppConstants.KEY_OBJ_CUSTOMER)
        if (customerObj != null) {
            dialogAddCustomerBinding?.edtName?.setText(customerObj.name)
            dialogAddCustomerBinding?.edtAddress?.setText(customerObj.address)
            dialogAddCustomerBinding?.edtPhone?.setText(customerObj.phoneNumber)
            dialogAddCustomerBinding?.edtLeftDiop?.setText(customerObj.leftDiop)
            dialogAddCustomerBinding?.edtRightDiop?.setText(customerObj.rightDiop)
            dialogAddCustomerBinding?.edtGlasses?.setText(customerObj.glassesType)
            dialogAddCustomerBinding?.edtDob?.setText(customerObj.dob)
            dialogAddCustomerBinding?.btnAddCustomer?.visibility = View.GONE
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        mCurrentGender = dialogAddCustomerBinding?.spinGender?.selectedItem.toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    private fun setUpCategorySpinner() {
        ArrayAdapter.createFromResource(
            _mActivity?.baseContext!!,
            R.array.gender_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dialogAddCustomerBinding?.spinGender?.adapter = adapter
            dialogAddCustomerBinding?.spinGender?.onItemSelectedListener = this
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            dialogAddCustomerBinding?.btnAddCustomer -> {
                val dataManager: DataManager? = (_mActivity?.application as QHApplication).mAppComponent?.getDataManager()
                addCustomer(dataManager)
            }
        }
    }

    private fun addCustomer(dataManager: DataManager?) {
        val customer = Customer()
        if (customer.name.isEmpty()) {
            Toast.makeText(_mActivity, "Tên khách hàng không được bỏ trống", Toast.LENGTH_SHORT).show()
        } else {
            customer.name = dialogAddCustomerBinding?.edtName?.text.toString()
            customer.address = dialogAddCustomerBinding?.edtAddress?.text.toString()
            customer.phoneNumber = dialogAddCustomerBinding?.edtPhone?.text.toString()
            customer.leftDiop = dialogAddCustomerBinding?.edtLeftDiop?.text.toString()
            customer.rightDiop = dialogAddCustomerBinding?.edtRightDiop?.text.toString()
            customer.glassesType = dialogAddCustomerBinding?.edtGlasses?.text.toString()
            customer.dob = dialogAddCustomerBinding?.edtDob?.text.toString()
            customerList?.add(customer)
            customerAdapter?.update(customerList)
            dataManager?.saveCustomer(customer)
            dismiss()
        }
    }
}