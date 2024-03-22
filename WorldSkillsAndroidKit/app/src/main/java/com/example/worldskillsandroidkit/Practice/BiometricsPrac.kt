package com.example.worldskillsandroidkit.Practice

import android.content.Context
import android.hardware.biometrics.BiometricPrompt.AuthenticationCallback
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.worldskillsandroidkit.data.BiometricsManager

class BiometricsPrac(private val context: Context) {

    fun checkAuthentication(): Boolean {
        val authenticationManager = BiometricManager.from(context)
        return when(authenticationManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)) {

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

    fun authenticationCallback(onSucceedCallback: () -> Unit): BiometricPrompt {
      return   BiometricPrompt(context as Fragment, ContextCompat.getMainExecutor(context), object: BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()

            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                onSucceedCallback()
            }

        })
    }

    fun authenticationPrompt():BiometricPrompt.PromptInfo {
       var prompt = BiometricPrompt.PromptInfo.Builder().setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG).setTitle("Authetnicate").setNegativeButtonText("Close").setDescription("Find alowed authetnicators").build()
        return prompt

    }


}