package com.marcelo.motivation.utils

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt


class ThemeUtils {
    companion object {
        @ColorInt
        fun getThemeColor(context: Context, @AttrRes attributeColor: Int): Int {
            val value = TypedValue()
            context.getTheme().resolveAttribute(attributeColor, value, true)
            return value.data
        }
    }
}