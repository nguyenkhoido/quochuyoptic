package com.vn.quochuyapplication.ui.input

import android.view.View
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
import com.vn.quochuyapplication.ui.company.CompanyActivity

class ImportFragment : BaseFragment<ImportPresenter>(), CompanyAdapter.ItemCompanyClick, IImportView {
    var companyAdapter: CompanyAdapter? = null
    var companyList: ArrayList<Company?>? = null

    companion object {
        fun newInstance(): ImportFragment {
            return ImportFragment()
        }
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

    override fun getLayoutResId() = R.layout.fragment_import

    override fun initInject() {
        fragmentComponent().inject(this)
    }

    fun showAddItemDialog() {
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

    fun refreshAdapter() {
        companyAdapter?.update(companyList)
    }

    override fun onItemClick(company: Company?) {
        CompanyActivity.starter(requireActivity(), company?.name ?: "null")
    }
}
