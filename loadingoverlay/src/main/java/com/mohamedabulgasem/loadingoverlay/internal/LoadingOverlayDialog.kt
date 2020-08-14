@file:SuppressLint("ViewConstructor")

package com.mohamedabulgasem.loadingoverlay.internal

import android.annotation.*
import android.app.*
import android.content.*
import android.view.WindowManager.LayoutParams.*
import android.widget.*
import androidx.annotation.*
import com.airbnb.lottie.*
import com.airbnb.lottie.LottieDrawable.*

internal class LoadingOverlayDialog(
    context: Activity,
    @RawRes val rawRes: Int,
    dimensDp: Int,
    dimAmount: Float,
    isCancellable: Boolean,
    onShowListener: (() -> Unit)?,
    onCancelListener: (() -> Unit)?,
    onDismissListener: (() -> Unit)?
) : Dialog(context) {

    init {
        val animationView: LottieAnimationView? = when (rawRes) {
            0 -> null
            else -> LottieAnimationView(context)
                .apply {
                    setAnimation(rawRes)
                    repeatCount = INFINITE
                }
        }
        setBackground(dimAmount)
        setCancelable(isCancellable)
        setListeners(
            animationView,
            onShowListener,
            onCancelListener,
            onDismissListener
        )
        setContentView(
            context,
            dimensDp,
            animationView
        )
    }

    private fun setBackground(dimAmount: Float) {
        window?.apply {
            setBackgroundDrawableResource(android.R.color.transparent)
            addFlags(FLAG_DIM_BEHIND)
            setDimAmount(dimAmount)
        }
    }

    private fun setListeners(
        animationView: LottieAnimationView?,
        onShowListener: (() -> Unit)?,
        onCancelListener: (() -> Unit)?,
        onDismissListener: (() -> Unit)?
    ) {
        setOnShowListener {
            animationView?.playAnimation()
            onShowListener?.invoke()
        }
        setOnCancelListener {
            onCancelListener?.invoke()
        }
        setOnDismissListener {
            animationView?.cancelAnimation()
            onDismissListener?.invoke()
        }
    }

    private fun setContentView(
        context: Context,
        dimensDp: Int,
        animationView: LottieAnimationView?
    ) {
        val rootView = FrameLayout(context).apply {
            val dimenPxs = context.dpToPixel(dimensDp)
            val loadingView = animationView ?: ProgressBar(context)
            addView(loadingView, dimenPxs, dimenPxs)
        }
        setContentView(rootView)
    }

}