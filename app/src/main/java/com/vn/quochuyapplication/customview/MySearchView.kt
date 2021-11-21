package com.vn.quochuyapplication.customview

import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

import com.vn.quochuyapplication.R
import com.vn.quochuyapplication.databinding.LayoutSearchViewBinding
import com.vn.quochuyapplication.utils.FontUtils
import kotlinx.android.synthetic.main.layout_search_view.view.*

class MySearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    var mOnSearchViewClickListener: OnSearchViewClick? = null
    var mOSearchKeyChangeListener: OnSearchKeyChangeListener? = null
    var searchViewBinding: LayoutSearchViewBinding? = null

    init {
        setupAttributes(attrs)
        initView(context)
        initListener()
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        //val inflated = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        //searchViewBinding = LayoutSearchViewBinding.inflate(inflated, this, false)
        searchViewBinding = LayoutSearchViewBinding.bind(View.inflate(context, R.layout.layout_search_view, this))

        attrs?.let {
            val typedArray =
                context.theme.obtainStyledAttributes(attrs, R.styleable.SearchView, 0, 0)

            typedArray.getString(R.styleable.SearchView_textHint)?.let { setLabelHint(it) }

            val iFontSize = typedArray.getDimension(
                R.styleable.SearchView_fontSize,
                resources.getDimension(R.dimen._15ssp)
            )
            iFontSize.let { searchViewBinding?.edtLabel?.setTextSize(TypedValue.COMPLEX_UNIT_PX, it) }

              val iFontTypeFaceName = typedArray.getString(R.styleable.SearchView_fontStyleName)
              iFontTypeFaceName?.let {
                  searchViewBinding?.edtLabel?.let { it1 -> FontUtils.setCustomFont(context, it1, it) }
              } ?: kotlin.run {
                  searchViewBinding?.edtLabel?.let { FontUtils.setCustomFont(context, it, FontUtils.FONT_MEDIUM) }
              }
        }
    }

    /**
     * Init view.
     *
     * @param context the context
     */
    private fun initView(context: Context) {
        setBgColor(R.color.white)
        setAlphaSearchView(0.5f)
    }

    private fun initListener() {
        searchViewBinding?.ivLeft?.setOnClickListener {
            mOnSearchViewClickListener?.onIconLeftClick()
        }

        searchViewBinding?.ivRight?.setOnClickListener {
            mOnSearchViewClickListener?.onIconRightClick()

            searchViewBinding?.edtLabel?.setText("")

            searchViewBinding?.edtLabel?.requestFocus()
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(searchViewBinding?.edtLabel, InputMethodManager.SHOW_IMPLICIT)
        }

        setOnClickListener {
            mOnSearchViewClickListener?.onItemClick()
            searchViewBinding?.edtLabel?.requestFocus()
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(edt_label, InputMethodManager.SHOW_IMPLICIT)
        }

        searchViewBinding?.edtLabel?.setOnFocusChangeListener { _, hasFocus ->
            mOnSearchViewClickListener?.onFocusChangeSearchViewListener(hasFocus)
            searchViewBinding?.ivRight?.visibility = if (hasFocus) View.VISIBLE else View.GONE

            if (hasFocus) {
                setAlphaSearchView(1.0f)
                searchViewBinding?.edtLabel?.text?.let {
                    if (!searchViewBinding?.edtLabel?.text.isNullOrEmpty()) {
                        searchViewBinding?.edtLabel?.setSelection(searchViewBinding?.edtLabel?.text.toString().length)
                    }
                }
                searchViewBinding?.ivRight?.visibility = if (!TextUtils.isEmpty(searchViewBinding?.edtLabel?.text)) View.VISIBLE
                else View.GONE
            } else {
                setAlphaSearchView(0.5f)
                searchViewBinding?.edtLabel?.text?.let {
                    iv_right?.visibility = if (!TextUtils.isEmpty(searchViewBinding?.edtLabel?.text)) View.VISIBLE
                    else View.GONE
                }
            }
        }
        searchViewBinding?.edtLabel?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mOSearchKeyChangeListener?.onSearchKeyChanged(p0.toString())
                searchViewBinding?.ivRight?.visibility = if (!TextUtils.isEmpty(edt_label?.text)) View.VISIBLE
                else View.GONE
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    /**
     * set Label.
     *
     * @param label
     */
    fun setLabel(label: String) {
        label?.let {  searchViewBinding?.edtLabel?.setText(it) }
    }

    /**
     * set label Hint.
     *
     * @param labelHint
     */
    fun setLabelHint(labelHint: String) {
        labelHint.let { searchViewBinding?.edtLabel?.hint = labelHint }
    }

    /**
     * Set image left
     *
     * @param imageResource
     */
    fun setIconLeft(imageResource: Int) {
        if (imageResource != 0) {
            searchViewBinding?.ivLeft?.setImageResource(imageResource)
            searchViewBinding?.ivLeft?.visibility = View.VISIBLE
        } else {
            searchViewBinding?.ivLeft?.visibility = View.GONE
        }
    }

    /**
     * Set image right
     *
     * @param imageResource
     */
    fun setIconRight(imageResource: Int) {
        if (imageResource != 0) {
            searchViewBinding?.ivRight?.setImageResource(imageResource)
            searchViewBinding?.ivRight?.visibility = View.VISIBLE
        } else {
            searchViewBinding?.ivRight?.visibility = View.GONE
        }
    }


    /**
     * Set text label color
     *
     * @param textColor
     */
    fun setTextColor(textColor: Int) {
        searchViewBinding?.edtLabel?.setTextColor(ContextCompat.getColor(context, textColor))
    }

    /**
     * Set BackGround Color
     *
     * @param bgColor
     */
    fun setBgColor(bgColor: Int) {
        setBackgroundColor(ContextCompat.getColor(context, bgColor))
    }

    fun setAlphaSearchView(iAlpha: Float) {
        searchViewBinding?.ivLeft?.alpha = iAlpha
        searchViewBinding?.edtLabel?.alpha = iAlpha
        searchViewBinding?.ivRight?.alpha = iAlpha
    }

    fun getEdtSearch(): EditText {
        return edt_label
    }


    fun setOnHeaderViewClickListener(onSearchViewClick: OnSearchViewClick) {
        mOnSearchViewClickListener = onSearchViewClick
    }


    fun setOnSearchKeyChangeListener(listener: OnSearchKeyChangeListener) {
        mOSearchKeyChangeListener = listener
    }

    interface OnSearchViewClick {
        fun onIconLeftClick()
        fun onIconRightClick()
        fun onItemClick()
        fun onFocusChangeSearchViewListener(hasFocus: Boolean)
    }

    interface OnSearchKeyChangeListener {
        fun onSearchKeyChanged(key: String)
    }
}