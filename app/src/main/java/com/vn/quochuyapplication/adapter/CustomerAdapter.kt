package com.vn.quochuyapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vn.quochuyapplication.R
import com.vn.quochuyapplication.base.BaseRecyclerAdapter
import com.vn.quochuyapplication.base.BaseViewHolder
import com.vn.quochuyapplication.data.model.Customer

class CustomerAdapter constructor(
    context: Context,
    companyList: ArrayList<Customer?>,
    private val itemCompanyClick: ItemCustomerClick
) : BaseRecyclerAdapter<Customer?, CustomerAdapter.CustomerViewHolder?>(context, companyList) {
    interface ItemCustomerClick {
        fun onItemClick(customer: Customer?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_customer, parent, false)
        return CustomerViewHolder(itemView, itemCompanyClick)
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        holder.itemCustomerName.text = dataList[position]?.name
        holder.itemCustomerAddress.text = dataList[position]?.address
    }

    inner class CustomerViewHolder(
        customerView: View,
        private val itemCustomerClick: ItemCustomerClick?
    ) : BaseViewHolder(customerView), View.OnClickListener {
        val itemCustomerName: TextView =
            customerView.findViewById<View>(R.id.text_customer_name) as TextView
        val itemCustomerAddress: TextView =
            customerView.findViewById<View>(R.id.text_address) as TextView

        override fun onBind(position: Int) {}
        override fun onClick(p0: View) {
            this.itemCustomerClick!!.onItemClick(dataList[adapterPosition])
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

}