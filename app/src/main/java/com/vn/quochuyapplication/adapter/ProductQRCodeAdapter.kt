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
import com.vn.quochuyapplication.data.model.ProductId

class ProductQRCodeAdapter(
    context: Context,
    qrCodeList: MutableList<ProductId>,
    private val itemProductClick: ItemProductClick
) : BaseRecyclerAdapter<ProductId, ProductQRCodeAdapter.ProductViewHolder?>(context, qrCodeList) {

    interface ItemProductClick {
        fun onItemClick(iProduct: ProductId)
        fun onItemLongClick(iProduct: ProductId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_code, parent, false)
        return ProductViewHolder(itemView, itemProductClick)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product: ProductId = dataList[position]
        val itemProductName = holder.itemQRCodeValue
        itemProductName.text = "Mã QR Code: " + product.productQRCode
    }


    inner class ProductViewHolder(
        productView: View,
        private val itemProductClick: ItemProductClick
    ) : BaseViewHolder(productView), View.OnClickListener, View.OnLongClickListener {
        val itemQRCodeValue: TextView = productView.findViewById(R.id.text_product_code)

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