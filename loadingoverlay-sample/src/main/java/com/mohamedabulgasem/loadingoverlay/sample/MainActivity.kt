package com.mohamedabulgasem.loadingoverlay.sample

import android.content.*
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.mohamedabulgasem.loadingoverlay.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit.*

class MainActivity : AppCompatActivity() {

    private val loadingOverlay: LoadingOverlay by lazy {
        LoadingOverlay.with(
            context = this,
            animation = LoadingAnimation.LOADING_SPINNER
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        first_screen.setOnClickListener {
            loadingOverlay.showFor(4, SECONDS) {
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            }
        }
    }

}