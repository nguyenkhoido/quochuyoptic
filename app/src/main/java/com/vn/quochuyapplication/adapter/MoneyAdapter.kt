package com.vn.quochuyapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vn.quochuyapplication.R
import com.vn.quochuyapplication.adapter.MoneyAdapter.MoneyViewHolder
import com.vn.quochuyapplication.base.BaseRecyclerAdapter
import com.vn.quochuyapplication.base.BaseViewHolder
import com.vn.quochuyapplication.data.model.Money

class MoneyAdapter constructor(
    context: Context,
    moneyList: MutableList<Money?>,
    private val itemMoneyClick: ItemMoneyClick
) : BaseRecyclerAdapter<Money?, MoneyViewHolder?>(context, moneyList) {

    interface ItemMoneyClick {
        fun onItemClick(money: Money?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoneyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_money, parent, false)
        return MoneyViewHolder(itemView, itemMoneyClick)
    }

    override fun onBindViewHolder(holder: MoneyViewHolder, position: Int) {
        val money = dataList[position]
        holder.itemProductCode?.text = money?.productCode.toString()
        holder.itemMoneyPrice?.text = money?.productPrice.toString()
        holder.itemSellDate?.text = money?.sellDate.toString()
    }

    inner class MoneyViewHolder(
        itemMoneyView: View,
        private val itemMoneyClick: ItemMoneyClick?
    ) : BaseViewHolder(itemMoneyView), View.OnClickListener {

        val itemProductCode: TextView? = itemMoneyView.findViewById(R.id.text_product_code)
        val itemMoneyPrice: TextView? = itemMoneyView.findViewById(R.id.text_product_price)
        val itemSellDate: TextView? = itemMoneyView.findViewById(R.id.text_date)

        override fun onBind(i: Int) {

        }

        override fun onClick(v: View?) {
            itemMoneyClick?.onItemClick(dataList[adapterPosition])
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

}