package com.mohamedabulgasem.loadingoverlay.internal

import android.app.*
import java.util.concurrent.*
import com.mohamedabulgasem.loadingoverlay.*

internal class LoadingOverlayImpl(
    context: Activity,
    animation: LoadingAnimation,
    dimAmount: Float,
    isCancellable: Boolean,
    onShowListener: (() -> Unit)?,
    onCancelListener: (() -> Unit)?,
    onDismissListener: (() -> Unit)?
) : LoadingOverlay {

    private val overlayDialog: LoadingOverlayDialog by lazy {
        LoadingOverlayDialog(
            context,
            animation.rawRes,
            animation.dimens,
            dimAmount,
            isCancellable,
            onShowListener,
            onCancelListener,
            onDismissListener
        )
    }

    override fun show() = overlayDialog.show()

    override fun showFor(
        period: Long,
        unit: TimeUnit,
        action: (() -> Unit)?
    ) {
        overlayDialog.show()
        runAfter(period, unit) {
            dismiss()
            action?.invoke()
        }
    }

    override fun dismiss() = overlayDialog.dismiss()

    override fun isShowing() = overlayDialog.isShowing

}