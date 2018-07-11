package com.inatel.pokedex.views

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.inatel.pokedex.utils.CameraPermissionHelper
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import org.jetbrains.anko.longToast

/**
 *
 * @author lucasmarciano
 * @version 1.0.0
 */
class CapturePokemonActivity : AppCompatActivity(), CameraPermissionHelper.CameraPermissionsGrantedCallback, ZXingScannerView.ResultHandler {
    // Atributos staticos
    companion object {
        var mUrl: String = ""
    }

    private var mScannerView: ZXingScannerView? = null

    override fun handleResult(rawResult: Result?) {
        mUrl = rawResult!!.text
        longToast("URL: $mUrl")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mScannerView = ZXingScannerView(this)
        setContentView(mScannerView)
    }

    override fun onResume() {
        super.onResume()
        if (isPermissionGranted()) {
            mScannerView!!.setResultHandler(this)
            mScannerView!!.startCamera()
        } else {
            navigateToCaptureFragment()
        }
    }


    public override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera()
    }

    override fun navigateToCaptureFragment() {
        super.onResume()
        if (!isPermissionGranted()) {
            CameraPermissionHelper.newInstance().show(supportFragmentManager, CameraPermissionHelper::class.java.simpleName)
        }
    }

    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
    }
}
