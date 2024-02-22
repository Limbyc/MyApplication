package com.valance.myapplication.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable

object ImageUtils {

    fun applyRoundedCornerMask(originalBitmap: Bitmap, maskDrawable: Drawable): Bitmap {
        val resultBitmap = Bitmap.createBitmap(originalBitmap.width, originalBitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(resultBitmap)
        maskDrawable.setBounds(0, 0, originalBitmap.width, originalBitmap.height)
        maskDrawable.draw(canvas)
        val paint = Paint()
        paint.xfermode = android.graphics.PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(originalBitmap, 0f, 0f, paint)
        return resultBitmap
    }
}
