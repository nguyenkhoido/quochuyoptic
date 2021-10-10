package com.vn.quochuyapplication.utils

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

object FontUtils {

    const val FONT_BLACK = "fonts/Inter-Black.ttf"
    const val FONT_BOLD = "fonts/Inter-Bold.ttf"
    const val FONT_EXTRA_BOLD = "fonts/Inter-ExtraBold.ttf"
    const val FONT_EXTRA_LIGHT = "fonts/Inter-ExtraLight.ttf"
    const val FONT_LIGHT = "fonts/Inter-Light.ttf"
    const val FONT_MEDIUM = "fonts/Inter-Medium.ttf"
    const val FONT_REGULAR = "fonts/Inter-Regular.ttf"
    const val FONT_SEMI_BOLD = "fonts/Inter-SemiBold.ttf"
    const val FONT_THIN = "fonts/Inter-Thin.ttf"

    // Contain list type face which only init 1 time when app running
    var mTypefaceList: HashMap<String, Typeface> = HashMap()

    /**
     * Get typeFace from asset
     */
    fun getTypeface(context: Context, fontPath: String): Typeface? {
        if(mTypefaceList.containsKey(fontPath)) {
            val typefaceCached = mTypefaceList.get(fontPath)
            if(typefaceCached != null){
                return typefaceCached
            }
        }
        return try {
            var typeface = Typeface.createFromAsset(context.assets, fontPath)
            mTypefaceList.put(fontPath, typeface)
            typeface
        } catch (ex: Exception) {
            return null
        }
    }

    /**
     * Get font path from font style
     */
    fun getFontPathFromStyle(fontStyle: String): String? {
        try {
            when (fontStyle) {
                null -> FONT_REGULAR
                "1" -> return FONT_BLACK
                "2" -> return FONT_BOLD
                "3" -> return FONT_EXTRA_BOLD
                "4" -> return FONT_EXTRA_LIGHT
                "5" -> return FONT_LIGHT
                "6" -> return FONT_MEDIUM
                "7" -> return FONT_REGULAR
                "8" -> return FONT_SEMI_BOLD
                "9" -> return FONT_THIN
            }
        } catch (ex: Exception) {
            return FONT_REGULAR
        }
        return FONT_REGULAR
    }

    /**
     * Set font for view
     */
    fun setCustomFont(context: Context, pView: View, asset: String): Boolean {
        var asset = getFontPathFromStyle(asset)
        var tf: Typeface?

        try {
            tf = asset?.let { getTypeface(context, it) }

            when (pView) {
                is TextView -> pView.typeface = tf
                is EditText -> pView.typeface = tf
                is Button -> pView.typeface = tf
            }
        } catch (e: Exception) {
            return false
        }
        return true
    }

    fun setFontStyle(context: Context, pView: View, fontStyle: FontStyle) {
        setCustomFont(context, pView, fontStyle.value)
    }


}