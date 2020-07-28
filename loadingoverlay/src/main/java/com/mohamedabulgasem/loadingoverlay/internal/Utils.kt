package com.mohamedabulgasem.loadingoverlay.internal

import android.content.*
import android.os.*
import android.util.*
import java.util.concurrent.*

internal fun runAfter(period: Long, unit: TimeUnit, code: () -> Unit): Boolean =
    Handler().postDelayed(code, unit.toMillis(period))

internal fun Context.dpToPixel(dimenDp: Int): Int = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dimenDp.toFloat(),
    resources.displayMetrics
).toInt()