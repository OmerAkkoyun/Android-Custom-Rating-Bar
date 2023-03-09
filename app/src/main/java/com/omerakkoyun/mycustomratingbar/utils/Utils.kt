package com.omerakkoyun.mycustomratingbar.utils

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat


fun ImageView.setTint(@ColorRes colorRes: Int) {
    ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(getColor(context, colorRes)))
}
fun ImageView.setTintColor(@ColorInt colorRes: Int) {
    ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(colorRes))
}


fun getColor(context: Context,@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(context,colorRes)
}