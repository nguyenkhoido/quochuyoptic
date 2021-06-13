package com.vn.quochuyapplication.base


interface BaseView {
  fun displayError(message: String)
  fun showError(message: String?)
  fun stateError()
  fun stateCompleted()
  fun stateLoading()
}