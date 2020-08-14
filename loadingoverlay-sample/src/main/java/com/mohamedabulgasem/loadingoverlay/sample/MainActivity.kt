package com.mohamedabulgasem.loadingoverlay.sample

import android.content.*
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.mohamedabulgasem.loadingoverlay.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val loadingOverlay: LoadingOverlay by lazy {
        LoadingOverlay.with(
            context = this,
            dimAmount = 0.8f
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        first_screen.setOnClickListener {
            loadingOverlay.showFor(4, TimeUnit.SECONDS) {
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            }
        }
    }

    fun onCreateNewAccountButtonClicked() {
        val loadingOverlay = LoadingOverlay.with(
            context = this,

            // Specify whether the overlay is cancelable on
            // back presses or screen touches, default is false.
            isCancellable = true,

            onShowListener = {
                // Optionally run some code when the overlay is shown.
            },

            onCancelListener = {
                // Optionally run some code if isCancellable
                // is set to true and the overlay is cancelled.
            },

            onDismissListener = {
                // Optionally run some code when the overlay is dismissed.
            }
        )

        // ...

        // You can use the showFor function to only display the
        // loading overlay for the specified period of time.
        loadingOverlay.showFor(5, TimeUnit.SECONDS) {
            // Optionally run some code after the set period has
            // passed and the loading overlay has been dismissed.
        }


        loadingOverlay.show()
        // ...
        loadingOverlay.dismiss()
    }

}