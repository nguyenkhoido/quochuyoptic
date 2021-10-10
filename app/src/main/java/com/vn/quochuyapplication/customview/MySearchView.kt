package com.vn.quochuyapplication.customview

import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

import com.vn.quochuyapplication.R
import com.vn.quochuyapplication.utils.FontUtils
import kotlinx.android.synthetic.main.layout_search_view.view.*

class MySearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    var mOnSearchViewClickListener: OnSearchViewClick? = null
    var mOSearchKeyChangeListener: OnSearchKeyChangeListener? = null

    init {
        setupAttributes(attrs)
        initView(context)
        initListener()
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        View.inflate(context, R.layout.layout_search_view, this)

        attrs?.let { attrsVal ->
            val typedArray =
                context.theme.obtainStyledAttributes(attrs, R.styleable.SearchView, 0, 0)

            typedArray.getString(R.styleable.SearchView_textHint)?.let { setLabelHint(it) }

            val iFontSize = typedArray.getDimension(
                R.styleable.SearchView_fontSize,
                resources.getDimension(R.dimen._15ssp)
            )
            iFontSize?.let { edt_label.setTextSize(TypedValue.COMPLEX_UNIT_PX, it) }

            val iFontTypeFaceName = typedArray.getString(R.styleable.SearchView_fontStyleName)
            iFontTypeFaceName?.let {
                FontUtils.setCustomFont(context, edt_label, it)
            } ?: kotlin.run {
                FontUtils.setCustomFont(context, edt_label, FontUtils.FONT_MEDIUM)
            }
        }
    }

    /**
     * Init view.
     *
     * @param context the context
     */
    open fun initView(context: Context) {
        setBgColor(R.color.white)
        setAlphaSearchView(0.5f)
    }

    private fun initListener() {
        iv_left?.setOnClickListener {
            mOnSearchViewClickListener?.let { it.onIconLeftClick() }
        }

        iv_right?.setOnClickListener {
            mOnSearchViewClickListener?.let { it.onIconRightClick() }

            edt_label?.setText("")

            edt_label?.requestFocus()
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(edt_label, InputMethodManager.SHOW_IMPLICIT)
        }

        setOnClickListener {
            mOnSearchViewClickListener?.let { it.onItemClick() }
            edt_label?.requestFocus()
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(edt_label, InputMethodManager.SHOW_IMPLICIT)
        }

        edt_label?.setOnFocusChangeListener { _, hasFocus ->
            mOnSearchViewClickListener?.onFocusChangeSearchViewListener(hasFocus)
            iv_right?.visibility = if (hasFocus) View.VISIBLE else View.GONE

            if (hasFocus) {
                setAlphaSearchView(1.0f)
                edt_label?.text?.let {
                    if (!edt_label.text.isNullOrEmpty()) {
                        edt_label.setSelection(edt_label.text.length)
                    }
                }
                iv_right?.visibility = if (!TextUtils.isEmpty(edt_label?.text)) View.VISIBLE
                else View.GONE
            } else {
                setAlphaSearchView(0.5f)
                edt_label?.text?.let {
                    iv_right?.visibility = if (!TextUtils.isEmpty(edt_label?.text)) View.VISIBLE
                    else View.GONE
                }
            }
        }
        edt_label?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mOSearchKeyChangeListener?.onSearchKeyChanged(p0.toString())
                iv_right?.visibility = if (!TextUtils.isEmpty(edt_label?.text)) View.VISIBLE
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
        label?.let { edt_label.setText(it) }
    }

    /**
     * set label Hint.
     *
     * @param labelHint
     */
    fun setLabelHint(labelHint: String) {
        labelHint?.let { edt_label.hint = labelHint }
    }

    /**
     * Set image left
     *
     * @param imageResource
     */
    fun setIconLeft(imageResource: Int) {
        if (imageResource != 0) {
            iv_left?.setImageResource(imageResource)
            iv_left?.visibility = View.VISIBLE
        } else {
            iv_left?.visibility = View.GONE
        }
    }

    /**
     * Set image right
     *
     * @param imageResource
     */
    fun setIconRight(imageResource: Int) {
        if (imageResource != 0) {
            iv_right?.setImageResource(imageResource)
            iv_right?.visibility = View.VISIBLE
        } else {
            iv_right?.visibility = View.GONE
        }
    }


    /**
     * Set text label color
     *
     * @param textColor
     */
    fun setTextColor(textColor: Int) {
        edt_label?.setTextColor(ContextCompat.getColor(context, textColor))
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
        iv_left.alpha = iAlpha
        edt_label.alpha = iAlpha
        iv_right.alpha = iAlpha
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