package com.lucasmarciano.pokedex.views.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.lucasmarciano.pokedex.R
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {
    private var mDelayHandler: Handler? = null
    private var SPLASH_DELAY: Long = 3000

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            startActivity<LoginCreateUserActivity>()
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mDelayHandler = Handler()
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
    }

    override fun onDestroy() {
        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }
}
