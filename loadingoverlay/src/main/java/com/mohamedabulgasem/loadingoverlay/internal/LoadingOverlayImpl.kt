package com.mohamedabulgasem.loadingoverlay.internal

import android.app.*
import com.mohamedabulgasem.loadingoverlay.*

internal class LoadingOverlayImpl(
    context: Activity,
    dimAmount: Float,
    isCancellable: Boolean,
    onShowListener: (() -> Unit)?,
    onCancelListener: (() -> Unit)?,
    onDismissListener: (() -> Unit)?
) : LoadingOverlay {

    private val overlayDialog: LoadingOverlayDialog by lazy {
        LoadingOverlayDialog(
            context,
            dimAmount,
            isCancellable,
            onShowListener,
            onCancelListener,
            onDismissListener
        )
    }

    override fun show() = overlayDialog.show()
    override fun dismiss() = overlayDialog.dismiss()
    override fun isShowing(): Boolean = overlayDialog.isShowing

}