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
        val lottieAnimationView = loadLottieAnimationView(rawRes)
        setBackground(dimAmount)
        setCancelable(isCancellable)
        setListeners(
            lottieAnimationView,
            onShowListener,
            onCancelListener,
            onDismissListener
        )
        setContentView(
            context,
            dimensDp,
            lottieAnimationView
        )
    }

    private fun loadLottieAnimationView(
        @RawRes rawRes: Int
    ): LottieAnimationView? = when (rawRes) {
        0 -> null
        else -> LottieAnimationView(context)
            .apply {
                setAnimation(rawRes)
                repeatCount = INFINITE
            }
    }

    private fun setBackground(dimAmount: Float) {
        window?.apply {
            setBackgroundDrawableResource(android.R.color.transparent)
            addFlags(FLAG_DIM_BEHIND)
            setDimAmount(dimAmount)
        }
    }

    private fun setListeners(
        lottieAnimationView: LottieAnimationView?,
        onShowListener: (() -> Unit)?,
        onCancelListener: (() -> Unit)?,
        onDismissListener: (() -> Unit)?
    ) {
        setOnShowListener {
            lottieAnimationView?.playAnimation()
            onShowListener?.invoke()
        }
        setOnCancelListener {
            onCancelListener?.invoke()
        }
        setOnDismissListener {
            lottieAnimationView?.cancelAnimation()
            onDismissListener?.invoke()
        }
    }

    private fun setContentView(
        context: Context,
        dimensDp: Int,
        lottieAnimationView: LottieAnimationView?
    ) {
        val rootView = FrameLayout(context).apply {
            val dimenPxs = context.dpToPixel(dimensDp)
            val loadingView = lottieAnimationView ?: ProgressBar(context)
            addView(loadingView, dimenPxs, dimenPxs)
        }
        setContentView(rootView)
    }

}