package com.vn.quochuyapplication.eventbus

import com.vn.quochuyapplication.data.model.IProduct

class AddProductEvent(eventCase: Int, product: IProduct) {

    var event: Int = -1
    var product: IProduct? = null

    companion object {
        val ADD_PRODUCT = 199
    }

    init {
        this.event = eventCase
        this.product = product
    }
}