package com.mohamedabulgasem.loadingoverlay

import android.app.*
import com.mohamedabulgasem.loadingoverlay.internal.*

interface LoadingOverlay {

    fun show() // Display the loading overlay on screen.
    fun dismiss() // Dismiss/remove the loading overlay from screen.
    fun isShowing(): Boolean // Whether the loading overlay is currently showing.

    companion object {

        fun with(
            context: Activity,
            dimAmount: Float = 0.5f,
            isCancellable: Boolean = false,
            onShowListener: (() -> Unit)? = null,
            onCancelListener: (() -> Unit)? = null,
            onDismissListener: (() -> Unit)? = null
        ): LoadingOverlay = LoadingOverlayImpl(
            context,
            dimAmount,
            isCancellable,
            onShowListener,
            onCancelListener,
            onDismissListener
        )

    }

}