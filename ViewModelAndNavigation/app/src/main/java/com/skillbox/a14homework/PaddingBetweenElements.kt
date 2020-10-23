package com.skillbox.a14homework

import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View
import androidx.recyclerview.widget.RecyclerView

// Класс, который используется для создания отступов между элементами. Это возможно благодаря наследованию от ItemDecoration
class PaddingBetweenElements(private val context: Context) : RecyclerView.ItemDecoration() {

    // Устанавливаем размер отступов в пикселях
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val indent = 5.dpToPixelsConversion(context = context)
        with(outRect) {
            left = indent
            right = indent
            top = indent
            bottom = indent
        }
    }

    //    Перевод DP в пиксели в зависимости от плотности экрана
    private fun Int.dpToPixelsConversion(context: Context): Int {
        val density = context.resources.displayMetrics.densityDpi
        val pixelsInDp = density / DisplayMetrics.DENSITY_DEFAULT
        return this * pixelsInDp
    }
}