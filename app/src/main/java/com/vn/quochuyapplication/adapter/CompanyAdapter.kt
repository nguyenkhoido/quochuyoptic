package com.vn.quochuyapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vn.quochuyapplication.R
import com.vn.quochuyapplication.adapter.CompanyAdapter.CompanyViewHolder
import com.vn.quochuyapplication.base.BaseRecyclerAdapter
import com.vn.quochuyapplication.base.BaseViewHolder
import com.vn.quochuyapplication.data.model.Company

class CompanyAdapter constructor(
    context: Context,
    companyList: ArrayList<Company?>,
    private val itemCompanyClick: ItemCompanyClick
) : BaseRecyclerAdapter<Company?, CompanyViewHolder?>(context, companyList) {
    interface ItemCompanyClick {
        fun onItemClick(company: Company?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_company, parent, false)
        return CompanyViewHolder(itemView, itemCompanyClick)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.itemCompanyName.text = dataList[position]?.name
        holder.itemCompanyCity.text = dataList[position]?.city
    }

    inner class CompanyViewHolder(
        companyView: View,
        private val itemCompanyClick: ItemCompanyClick?
    ) : BaseViewHolder(companyView), View.OnClickListener {
        val itemCompanyName: TextView =
            companyView.findViewById<View>(R.id.text_company_name) as TextView
        val itemCompanyCity: TextView =
            companyView.findViewById<View>(R.id.text_company_city) as TextView

        override fun onBind(position: Int) {}
        override fun onClick(p0: View) {
            this.itemCompanyClick!!.onItemClick(dataList[adapterPosition])
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

}