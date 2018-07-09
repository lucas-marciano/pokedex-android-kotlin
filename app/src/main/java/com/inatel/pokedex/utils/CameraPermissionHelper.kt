/*
 * Developed by: Inatel Competence Center
 * Copyright 2018, NONUS
 * All rights are reserved. Reproduction in whole or part is
 * prohibited without the written consent of the copyright owner.
 */
package com.inatel.pokedex.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.DialogFragment
import com.inatel.pokedex.R
import org.jetbrains.anko.alert
import org.jetbrains.anko.cancelButton

/**
 *
 * @author lucasmarciano
 * @version 1.0.0
 */
open class CameraPermissionHelper : DialogFragment() {
    companion object {
        val PERMISSION_REQUEST_CODE = 11

        fun newInstance(): CameraPermissionHelper {
            return CameraPermissionHelper()
        }
    }

    private var mContext: Context? = null
    private var listener: CameraPermissionsGrantedCallback? = null

    private var shouldResolve: Boolean = false
    private var shouldRetry: Boolean = false
    private var externalGrantNeeded: Boolean = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
        if (context is CameraPermissionsGrantedCallback) {
            listener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.PermissionsDialogFragmentStyle)
        isCancelable = false
        requestNecessaryPermissions()
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        if (shouldResolve) {
            if (externalGrantNeeded) {
                showAppSettingsDialog()
            } else if (shouldRetry) {
                showRetryDialog()
            } else {
                if (listener != null) {
                    listener!!.navigateToCaptureFragment()
                    dismiss()
                }
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        mContext = null
        listener = null
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        shouldResolve = true
        shouldRetry = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (i in permissions.indices) {
                val permission = permissions[i]
                val grantResult = grantResults[i]

                if (!shouldShowRequestPermissionRationale(permission) && grantResult != PackageManager.PERMISSION_GRANTED) {
                    externalGrantNeeded = true
                    return
                } else if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    shouldRetry = true
                    return
                }
            }
        } else {
            return
        }
    }

    private fun requestNecessaryPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(
                    Manifest.permission.CAMERA), PERMISSION_REQUEST_CODE)
        }
    }

    private fun showAppSettingsDialog() {
        mContext!!.alert("In order to take pictures. Please enable these permissions from the app settings.","Permissions Required"){
            positiveButton("App Settings") {
                val intent = Intent()
                val uri = Uri.fromParts("package", mContext!!.applicationContext.packageName, null)
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                intent.data = uri
                mContext!!.startActivity(intent)
                dismiss()
            }
            cancelButton { dismiss() }
        }
    }

    private fun showRetryDialog() {
        mContext!!.alert("In order to take pictures. Please enable these permissions from the app settings.","Permissions Declined"){
            positiveButton("Cool") {requestNecessaryPermissions()}
            cancelButton { dismiss() }

        }
    }

    interface CameraPermissionsGrantedCallback {
        fun navigateToCaptureFragment()
    }
}


