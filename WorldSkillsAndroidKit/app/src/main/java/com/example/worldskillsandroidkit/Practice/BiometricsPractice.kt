package com.example.worldskillsandroidkit.Practice

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

class BiometricManager(private val context: Context) {
    fun biometricCallback(onSuccess: () -> Unit, onFailure: () -> Unit): BiometricPrompt {
        var biometricCallback = BiometricPrompt(context as FragmentActivity, ContextCompat.getMainExecutor(context), object:
            BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
            }
        })
        return biometricCallback
    }

    fun biometricPrompt(): BiometricPrompt.PromptInfo {
        val biometricPrompt = BiometricPrompt.PromptInfo.Builder().setDescription("What").setTitle("Blah").setAllowedAuthenticators(BIOMETRIC_STRONG).setNegativeButtonText("Cancel").build()
        return biometricPrompt
    }

    fun biometricCheck(): Boolean{
        return when(BiometricManager.from(context).canAuthenticate(BIOMETRIC_STRONG)) {

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
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
}