package com.vn.quochuyapplication.base

interface BasePresenter<V : BaseView> {
  fun attachView(view: V)
  fun detachView()
}