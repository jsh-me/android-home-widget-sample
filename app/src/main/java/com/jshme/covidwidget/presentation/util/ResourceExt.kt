package com.jshme.covidwidget.presentation.util

import android.content.res.Resources
import android.util.TypedValue
import androidx.annotation.Dimension

fun Resources.pixelsFrom(value: Float, @Dimension dimension: Int = Dimension.DP): Float {
    return when (dimension) {
        Dimension.DP -> TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, displayMetrics)
        Dimension.SP -> TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, displayMetrics)
        else -> value // Dimension.PX
    }
}
