package com.mohamedabulgasem.loadingoverlay.sample

import android.os.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.mohamedabulgasem.loadingoverlay.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val loadingOverlay: LoadingOverlay by lazy {
        LoadingOverlay.with(
            context = this,
            dimAmount = 0.6f,
            isCancellable = true,
            onCancelListener = { showToast("Loading overlay cancelled!") }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_show.setOnClickListener {
            loadingOverlay.show()
            doAfter(5) {
                loadingOverlay.dismiss()
            }
        }
    }

    private fun showToast(text: String)
            = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

    private fun doAfter(seconds: Long, action: () -> Unit)
            = Handler().postDelayed(action, seconds * 1000)

}