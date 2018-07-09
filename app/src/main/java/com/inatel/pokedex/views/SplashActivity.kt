package com.inatel.pokedex.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.inatel.pokedex.R
import org.jetbrains.anko.startActivity

/**
 *
 * @author lucasmarciano
 * @version 1.0.0
 */
class SplashActivity : AppCompatActivity() {
    private var mDelayHandler: Handler? = null
    private var SPLASH_DELAY: Long = 3000

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            startActivity<PokemonsListActivity>()
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