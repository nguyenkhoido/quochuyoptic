package com.vn.quochuyapplication.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    abstract fun onBind(i: Int)
}