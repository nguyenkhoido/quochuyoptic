package com.vn.quochuyapplication.ui.money

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoneyViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}