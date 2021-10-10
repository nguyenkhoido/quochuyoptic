package com.vn.quochuyapplication.eventbus

import com.vn.quochuyapplication.data.model.Customer
import com.vn.quochuyapplication.data.model.IProduct

class AddCustomerEvent(eventCase: Int, customer: Customer) {
    var event: Int = -1
    var customer: Customer? = null

    companion object {
        val ADD_CUSTOMER = 168
    }

    init {
        this.event = eventCase
        this.customer = customer
    }
}