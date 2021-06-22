package com.vn.quochuyapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vn.quochuyapplication.R
import com.vn.quochuyapplication.base.BaseRecyclerAdapter
import com.vn.quochuyapplication.base.BaseViewHolder
import com.vn.quochuyapplication.data.model.IProduct

class ProductAdapter(
    context: Context,
    productList: MutableList<IProduct>,
    private val itemProductClick: ItemProductClick
) : BaseRecyclerAdapter<IProduct, ProductAdapter.ProductViewHolder?>(context, productList) {

    interface ItemProductClick {
        fun onItemClick(iProduct: IProduct)
        fun onItemLongClick(iProduct: IProduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView: View = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(itemView, itemProductClick)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product: IProduct = dataList[position]
        val itemProductName = holder.itemProductName
        itemProductName.text = "Tên SP: " + product.productName()
        val itemProductCode = holder.itemProductCode
        itemProductCode.text = "Mã SP: " + product.productCode()
    }


    inner class ProductViewHolder(
        productView: View,
        private val itemProductClick: ItemProductClick
    ) : BaseViewHolder(productView), View.OnClickListener, View.OnLongClickListener {
        val itemProductCode: TextView = productView.findViewById(R.id.text_product_code)
        val itemProductName: TextView = productView.findViewById(R.id.text_product_name)

        override fun onBind(position: Int) {}
        override fun onClick(p0: View) {
            this.itemProductClick.onItemClick(dataList[adapterPosition])
        }

        override fun onLongClick(p0: View): Boolean {
            this.itemProductClick.onItemLongClick(dataList[adapterPosition])
            return true
        }

        init {
            this.itemView.setOnClickListener(this)
            this.itemView.setOnLongClickListener(this)
        }
    }

}