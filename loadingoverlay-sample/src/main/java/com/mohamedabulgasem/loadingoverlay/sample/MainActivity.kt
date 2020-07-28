package com.mohamedabulgasem.loadingoverlay.sample

import android.os.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.mohamedabulgasem.loadingoverlay.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit.*

class MainActivity : AppCompatActivity() {

    private val loadingOverlay: LoadingOverlay by lazy {
        LoadingOverlay.with(this, LoadingAnimation.LOADING_SPINNER)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_show.setOnClickListener {
            loadingOverlay.showFor(5, SECONDS)
        }
    }

    private fun showToast(text: String) =
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

}