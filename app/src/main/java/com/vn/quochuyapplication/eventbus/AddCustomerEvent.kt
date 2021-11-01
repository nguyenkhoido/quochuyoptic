package com.vn.quochuyapplication.eventbus

import com.vn.quochuyapplication.data.model.Customer
import com.vn.quochuyapplication.data.model.IProduct

class AddCustomerEvent {
    var event: Int = -1
    var customerId: String? = null
    var customer: Customer? = null

    constructor(eventCase: Int, customerId: String) {
        this.event = eventCase
        this.customerId = customerId
    }

    constructor(eventCase: Int, customer: Customer) {
        this.event = eventCase
        this.customer = customer
    }

    companion object {
        val ADD_CUSTOMER = 168
        val UPDATE_CUSTOMER = 169
    }
}