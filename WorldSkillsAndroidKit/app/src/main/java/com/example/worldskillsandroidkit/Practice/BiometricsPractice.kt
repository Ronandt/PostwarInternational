package com.example.worldskillsandroidkit.Practice

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

class BiometricManagers(private val context: Context) {
    fun getBiometricCheck(): Boolean{
        return when(BiometricManager.from(context).canAuthenticate(BIOMETRIC_STRONG)) {

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                false
            }

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                false
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                false
            }

            BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
                false
            }

            BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
                false
            }

            BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> {
                false
            }

            BiometricManager.BIOMETRIC_SUCCESS -> {
                true
            }

            else -> {
                false
            }
        }
    }

    fun getBiometricCallback(onSuccess: () -> Unit, onFailure: () -> Unit) {

        BiometricPrompt(context as FragmentActivity, ContextCompat.getMainExecutor(context), object:
            BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
            }
            }).authenticate(BiometricPrompt.PromptInfo.Builder().build())
    }


}