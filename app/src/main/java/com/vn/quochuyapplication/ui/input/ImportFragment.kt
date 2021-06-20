package com.vn.quochuyapplication.ui.input

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.vn.quochuyapplication.R
import com.vn.quochuyapplication.adapter.CompanyAdapter
import com.vn.quochuyapplication.base.BaseFragment
import com.vn.quochuyapplication.data.model.Company
import com.vn.quochuyapplication.databinding.FragmentImportBinding
import com.vn.quochuyapplication.databinding.FragmentMoneyBinding
import com.vn.quochuyapplication.presenter.ImportPresenter
import com.vn.quochuyapplication.ui.company.CompanyActivity

class ImportFragment : BaseFragment<ImportPresenter>(), CompanyAdapter.ItemCompanyClick, IImportView {
    private var _importBinding: FragmentImportBinding? = null
    var companyAdapter: CompanyAdapter? = null
    var companyList: ArrayList<Company?>? = null

    companion object {
        fun newInstance(): ImportFragment {
            return ImportFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _importBinding = FragmentImportBinding.inflate(inflater, container, false)
        mRootView = _importBinding?.root!!
        return mRootView
    }

    override fun initViews() {
        mRootView.findViewById<FloatingActionButton>(R.id.float_add_supplier).setOnClickListener {
            showAddItemDialog()
        }
    }

    override fun initDataAndEvents() {
        companyList = ArrayList()
        companyList?.addAll(presenter.dataManager.getCompanyList())
        if (null != companyList) {
            val lnManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            companyAdapter = CompanyAdapter(requireContext(), companyList!!, this).also {
                val listView = mRootView.findViewById<RecyclerView>(R.id.recycle_company)
                listView.layoutManager = lnManager
                listView.adapter = it
            }
        }

    }

    override fun initInject() {
        fragmentComponent().inject(this)
    }

    private fun showAddItemDialog() {
        val dialogView: View = layoutInflater.inflate(R.layout.dialog_add_new_company, null)
        val alertDialog = MaterialAlertDialogBuilder(requireActivity()).setView(dialogView).create()
        val tvAdd = dialogView.findViewById<View>(R.id.tvOK) as TextView
        tvAdd.setOnClickListener {
            val company = Company()
            company.name = dialogView.findViewById<EditText>(R.id.edt_company_name).text.toString()
            company.city = "Việt Nam"
            presenter.dataManager.saveCompany(company)
            companyList?.add(company)
            refreshAdapter()
            alertDialog.dismiss()
        }
        val tvCancel = dialogView.findViewById<View>(R.id.tvCancel) as TextView
        tvCancel.setOnClickListener {
            alertDialog.dismiss()
        }
        alertDialog.show()
    }

    private fun refreshAdapter() {
        companyAdapter?.update(companyList)
    }

    override fun onItemClick(company: Company?) {
        CompanyActivity.starter(requireActivity(), company?.name ?: "null")
    }
}
