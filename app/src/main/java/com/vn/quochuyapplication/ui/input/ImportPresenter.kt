package com.vn.quochuyapplication.ui.input

import com.vn.quochuyapplication.base.RxPresenter
import com.vn.quochuyapplication.base.model.DataManager
import javax.inject.Inject

class ImportPresenter @Inject constructor(var dataManager: DataManager): RxPresenter<IImportView>() {

}