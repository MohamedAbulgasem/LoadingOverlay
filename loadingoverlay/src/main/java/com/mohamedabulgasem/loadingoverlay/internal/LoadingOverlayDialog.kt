package com.mohamedabulgasem.loadingoverlay.internal

import android.app.*
import android.util.*
import android.view.*
import android.view.WindowManager.LayoutParams.*
import android.widget.*

internal class LoadingOverlayDialog(
    context: Activity,
    dimAmount: Float,
    isCancellable: Boolean,
    onShowListener: (() -> Unit)?,
    onCancelListener: (() -> Unit)?,
    onDismissListener: (() -> Unit)?
) : Dialog(context) {

    init {
        window?.apply {
            setBackgroundDrawableResource(android.R.color.transparent)
            addFlags(FLAG_DIM_BEHIND)
            setDimAmount(dimAmount)
        }
        setContentView(loadingOverlayView)
        setCancelable(isCancellable)
        setOnShowListener { onShowListener?.invoke() }
        setOnCancelListener { onCancelListener?.invoke() }
        setOnDismissListener { onDismissListener?.invoke() }
    }

    private val loadingOverlayView: View
        get() {
            val rootView = FrameLayout(context)
            val progressBar = ProgressBar(context)
            val size = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                90f,
                context.resources.displayMetrics
            )
            rootView.addView(progressBar, size.toInt(), size.toInt())
            return rootView
        }

}