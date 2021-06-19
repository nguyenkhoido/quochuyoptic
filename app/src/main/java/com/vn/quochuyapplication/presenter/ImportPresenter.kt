package com.vn.quochuyapplication.presenter

import com.vn.quochuyapplication.base.RxPresenter
import com.vn.quochuyapplication.base.model.DataManager
import com.vn.quochuyapplication.ui.input.IImportView
import javax.inject.Inject

class ImportPresenter @Inject constructor(var dataManager: DataManager): RxPresenter<IImportView>() {

}