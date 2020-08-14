package com.mohamedabulgasem.loadingoverlay

import android.app.*
import androidx.annotation.*
import java.util.concurrent.*
import java.util.concurrent.TimeUnit.*
import com.mohamedabulgasem.loadingoverlay.internal.*
import com.mohamedabulgasem.loadingoverlay.LoadingAnimation.BuiltinAnimations.PROGRESS_BAR

interface LoadingOverlay {

    /** Display the loading overlay on screen. */
    fun show()

    /**
     * Display the loading overlay on screen for the set period.
     *
     * @param period the amount of time to show the loading overlay for.
     * @param unit the time unit of the given period, default is milliseconds.
     * @param action function to be invoked after the set period has passed.
     */
    fun showFor(
        period: Long,
        unit: TimeUnit = MILLISECONDS,
        action: (() -> Unit)? = null
    )

    /** Dismiss/remove the loading overlay from screen. */
    fun dismiss()

    /** Whether the loading overlay is currently showing. */
    fun isShowing(): Boolean

    companion object {

        /**
         * @param context host activity reference.
         * @param animation either one of the builtin loading animations or a custom one.
         * @param dimAmount the amount of background dim, from 0.0 for no dim to 1.0 for full dim.
         * @param isCancellable whether this overlay is cancelable on back presses or screen touches.
         * @param onShowListener listener to be invoked when the overlay is shown.
         * @param onCancelListener listener to be invoked if the overlay is cancelled.
         * @param onDismissListener listener to be invoked when the overlay is dismissed.
         */
        fun with(
            context: Activity,
            animation: LoadingAnimation = PROGRESS_BAR,
            dimAmount: Float = 0.5f,
            isCancellable: Boolean = false,
            onShowListener: (() -> Unit)? = null,
            onCancelListener: (() -> Unit)? = null,
            onDismissListener: (() -> Unit)? = null
        ): LoadingOverlay = LoadingOverlayImpl(
            context,
            animation,
            dimAmount,
            isCancellable,
            onShowListener,
            onCancelListener,
            onDismissListener
        )

    }

}

/**
 * @param rawRes lottie animation from a file (json or zip) in the raw directory.
 * @param dimens the width and height of the loading animation view, specified in dp.
 */
data class LoadingAnimation(
    @RawRes val rawRes: Int = 0,
    var dimens: Int
) {

    companion object BuiltinAnimations {
        val PROGRESS_BAR = LoadingAnimation(dimens = 70)
        val LOADING_SPINNER = LoadingAnimation(R.raw.lo_loading_spinner, 70)
        val FADING_PROGRESS = LoadingAnimation(R.raw.lo_fading_progress, 300)
    }

    fun withDimens(dimens: Int): LoadingAnimation = apply {
        this.dimens = dimens
    }

}