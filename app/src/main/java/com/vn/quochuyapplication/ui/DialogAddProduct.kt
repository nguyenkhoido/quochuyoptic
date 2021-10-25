package com.vn.quochuyapplication.ui

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import com.android.tools.build.jetifier.core.utils.Log
import com.vn.quochuyapplication.QHApplication
import com.vn.quochuyapplication.R
import com.vn.quochuyapplication.base.SimpleDialogFragment
import com.vn.quochuyapplication.base.model.DataManager
import com.vn.quochuyapplication.constant.AppConstants
import com.vn.quochuyapplication.constant.Category
import com.vn.quochuyapplication.data.model.*
import com.vn.quochuyapplication.databinding.DialogAddProductBinding
import com.vn.quochuyapplication.eventbus.AddProductEvent
import com.vn.quochuyapplication.ui.company.product.IProductListener
import com.vn.quochuyapplication.utils.StringUtils
import org.greenrobot.eventbus.EventBus

class DialogAddProduct : SimpleDialogFragment(), View.OnClickListener, TextWatcher {
    private var dialogAddProductBinding: DialogAddProductBinding? = null
    private var mCurrentCategory: String? = null
    private var mCompanyName: String? = null
    private var mItemProduct: IProduct? = null
    private var mIProductListener: IProductListener? = null

    companion object {
        fun newInstance(companyName: String, category: String): DialogAddProduct {
            val dialogAddProduct = DialogAddProduct()
            val args = Bundle()
            args.putString(AppConstants.PRODUCT_CATEGORY, category)
            args.putString(AppConstants.COMPANY_NAME, companyName)
            dialogAddProduct.arguments = args
            return dialogAddProduct
        }

        fun newInstance(product: IProduct?): DialogAddProduct {
            val dialogAddProductBK = DialogAddProduct()
            val args = Bundle()
            args.putParcelable(AppConstants.KEY_OBJ_PRODUCT, product)
            dialogAddProductBK.arguments = args
            return dialogAddProductBK
        }
    }

    fun setProductListener(listener: IProductListener) {
        mIProductListener = listener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialogAddProductBinding = DialogAddProductBinding.inflate(inflater, container, false)
        dialogAddProductBinding?.btnAddProduct?.setOnClickListener(this)
        dialogAddProductBinding?.edtPrice?.addTextChangedListener(this)
        dialogAddProductBinding?.edtPrice?.filters = arrayOf(InputFilter.LengthFilter(10))
        //setUpCategorySpinner()
        return dialogAddProductBinding?.root
    }

    override fun initData() {
        try {
            mCompanyName = requireArguments().getString(AppConstants.COMPANY_NAME)
            mCurrentCategory = requireArguments().getString(AppConstants.PRODUCT_CATEGORY)
            mItemProduct = requireArguments().getParcelable(AppConstants.KEY_OBJ_PRODUCT)
            if (null != mItemProduct) {
                dialogAddProductBinding?.btnAddProduct?.text = getString(R.string.edit_str)
                dialogAddProductBinding?.textMergedLabel?.visibility = View.VISIBLE
                dialogAddProductBinding?.textInputMerge?.visibility = View.VISIBLE
                val productCode = mItemProduct?.productCode()
                val productName = mItemProduct?.productName()
                val companyName = mItemProduct?.productCompanyName()
                val productQuantity = mItemProduct?.productQuantity().toString()
                val productPrice = mItemProduct?.productPrice().toString()
                val productCategory = mItemProduct?.productCategory()

                dialogAddProductBinding?.edtName?.setText(productName)
                dialogAddProductBinding?.edtCode?.setText(productCode)
                dialogAddProductBinding?.edtQuantity?.setText(productQuantity)
                dialogAddProductBinding?.edtPrice?.setText(productPrice)
                val productConvert = ProductConvert(productCode ?: "", companyName ?: "", productPrice, productQuantity, productCategory ?: "")
                dialogAddProductBinding?.edtMergedValue?.setText(QHApplication.getInstance().getGSon().toJson(productConvert))
            }
        } catch (ex: Exception) {
            Log.e("NGUYENDK", "DialogAddProduct " + ex.localizedMessage)
        }
    }

    /*override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        mCurrentCategory = dialogAddProductBinding?.spinProductCategory?.selectedItem.toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }*/

    private fun setUpCategorySpinner() {
        ArrayAdapter.createFromResource(
            _mActivity?.baseContext!!,
            R.array.product_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            //dialogAddProductBinding?.spinProductCategory?.adapter = adapter
            //dialogAddProductBinding?.spinProductCategory?.onItemSelectedListener = this
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            dialogAddProductBinding?.btnAddProduct -> {
                val dataManager: DataManager? = (_mActivity?.application as QHApplication).mAppComponent?.getDataManager()

                if (null != mItemProduct) {
                    updateProduct(
                        dataManager, dialogAddProductBinding?.edtName?.text.toString(),
                        dialogAddProductBinding?.edtCode?.text.toString(),
                        dialogAddProductBinding?.edtPrice?.text.toString().replace(",", "").toInt(),
                        dialogAddProductBinding?.edtQuantity?.text.toString().toInt(),
                        mCurrentCategory.toString()
                    )
                } else {
                    mItemProduct = addProduct(dataManager)
                    mItemProduct?.let { mIProductListener?.onAddProductSuccess(it) }
                }
                dismiss()
            }
        }
    }

    private fun updateProduct(dataManager: DataManager?, productName: String, productCode: String, productPrice: Int, productQuantity: Int, productCategory: String) {
        dataManager?.updateProductByCode(productName, productCode, productPrice, productQuantity, productCategory)
    }

    private fun addProduct(dataManager: DataManager?): IProduct {
        var iProduct: IProduct? = null
        when (mCurrentCategory) {
            Category.GONG_KINH -> {
                iProduct = newProduct(
                    Category.GONG_KINH, dialogAddProductBinding?.edtName?.text.toString(), mCompanyName ?: "", dialogAddProductBinding?.edtCode?.text.toString(),
                    dialogAddProductBinding?.edtQuantity?.text.toString().toInt(), dialogAddProductBinding?.edtPrice?.text.toString().replace(",", "").toInt()
                )
                dataManager?.saveFrame(iProduct)
            }
            Category.LENSE -> {
                iProduct = newProduct(
                    Category.LENSE, dialogAddProductBinding?.edtName?.text.toString(), mCompanyName ?: "", dialogAddProductBinding?.edtCode?.text.toString(),
                    dialogAddProductBinding?.edtQuantity?.text.toString().toInt(), dialogAddProductBinding?.edtPrice?.text.toString().replace(",", "").toInt()
                )
                dataManager?.saveLense(iProduct)
            }
            Category.TRONG_KINH -> {
                iProduct = newProduct(
                    Category.TRONG_KINH, dialogAddProductBinding?.edtName?.text.toString(), mCompanyName ?: "", dialogAddProductBinding?.edtCode?.text.toString(),
                    dialogAddProductBinding?.edtQuantity?.text.toString().toInt(), dialogAddProductBinding?.edtPrice?.text.toString().replace(",", "").toInt()
                )
                dataManager?.saveGlasses(iProduct)
            }
            Category.OTHER -> {
                iProduct = newProduct(
                    Category.OTHER, dialogAddProductBinding?.edtName?.text.toString(), mCompanyName ?: "", dialogAddProductBinding?.edtCode?.text.toString(),
                    dialogAddProductBinding?.edtQuantity?.text.toString().toInt(), dialogAddProductBinding?.edtPrice?.text.toString().replace(",", "").toInt()
                )
                dataManager?.saveOtherProduct(iProduct)
            }
        }
        return iProduct!!
    }

    private fun newProduct(category: String, productName: String, companyName: String, code: String, quantity: Int, price: Int): IProduct {
        when (category) {
            Category.GONG_KINH -> {
                val frame = Frame()
                frame.category = Category.GONG_KINH
                frame.productName = productName
                frame.companyName = companyName
                frame.productCode = code
                frame.quantity = quantity
                frame.price = price
                return frame

            }
            Category.LENSE -> {
                val lense = Lense()
                lense.category = Category.LENSE
                lense.productName = productName
                lense.companyName = companyName
                lense.productCode = code
                lense.quantity = quantity
                lense.price = price
                return lense

            }
            Category.TRONG_KINH -> {
                val glasses = Glasses()
                glasses.category = Category.TRONG_KINH
                glasses.productName = productName
                glasses.companyName = companyName
                glasses.productCode = code
                glasses.quantity = quantity
                glasses.price = price
                return glasses
            }
            else -> {
                val other = Other()
                other.category = Category.OTHER
                other.productName = productName
                other.companyName = companyName
                other.productCode = code
                other.quantity = quantity
                other.price = price
                return other
            }
        }
    }

    private lateinit var textBuilder: SpannableStringBuilder

    private fun setAmount(amount: Double) {
        dialogAddProductBinding?.edtPrice?.removeTextChangedListener(this)
        textBuilder = SpannableStringBuilder(StringUtils.formatCurrency(amount)!!)
        try {
            addComma()
            dialogAddProductBinding?.edtPrice?.setText(textBuilder.toString())
            dialogAddProductBinding?.edtPrice?.setSelection(textBuilder.length)
            dialogAddProductBinding?.edtPrice?.addTextChangedListener(this)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    private fun addComma() {
        val temp = textBuilder.toString().replace(",", "")
        val amount: Double = try {
            temp.toDouble()
        } catch (e: java.lang.Exception) {
            0.0
        }
        if (!textBuilder.toString().contains(",")) {
            textBuilder = SpannableStringBuilder(StringUtils.customFormatVND(amount))
        }
        if (textBuilder.toString() == "0") {
            textBuilder.length - 1
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (TextUtils.isEmpty(s.toString())) {
            dialogAddProductBinding?.edtPrice?.setText("0")
            return
        }
        val replaceString: String = s?.replace("""[,]""".toRegex(), "")!!
        setAmount(replaceString.toDouble())
    }

    override fun afterTextChanged(s: Editable?) {

    }
}