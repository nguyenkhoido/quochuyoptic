package com.vn.quochuyapplication.presenter

import com.vn.quochuyapplication.base.RxPresenter
import com.vn.quochuyapplication.base.model.DataManager
import com.vn.quochuyapplication.ui.export.IExportView
import javax.inject.Inject

class ExportPresenter @Inject constructor(var dataManager: DataManager) : RxPresenter<IExportView>() {

}