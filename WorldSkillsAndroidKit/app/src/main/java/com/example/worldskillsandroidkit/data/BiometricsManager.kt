package com.example.worldskillsandroidkit.data
/*
import android.content.Context
import android.hardware.biometrics.BiometricManager
import android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.example.worldskillsandroidkit.presentation.ImageResult

class BiometricsManager(private val context: Context, private val  authenticationFailure: () -> Unit, private val authenticationSuccess: () -> Unit) {

    fun authenticate(){
        val authCheck = authenticationCheck()

         if(!authCheck) {
            authenticationFailure()
         }
        val biometricPrompt = BiometricPrompt.PromptInfo.Builder().setTitle("Biometric Login").setSubtitle("Whatever").setDescription("").setAllowedAuthenticators(androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG).setNegativeButtonText("").build()
        val promptCallback = BiometricPrompt(context as FragmentActivity, ContextCompat.getMainExecutor(context), object: BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
               authenticationFailure()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                authenticationSuccess()
            }

            override fun onAuthenticationFailed() {
                authenticationFailure()
            }

        })

        promptCallback.authenticate(biometricPrompt)



    }

    private fun authenticationCheck():Boolean {
      val biometricManager =  androidx.biometric.BiometricManager.from(context)
        return when(biometricManager.canAuthenticate(androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG)) {

            androidx.biometric.BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
               false
            }

            androidx.biometric.BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
               false
            }

            androidx.biometric.BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                false
            }

            androidx.biometric.BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
                false
            }

            androidx.biometric.BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
                false
            }

            androidx.biometric.BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> {
                false
            }

            androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS -> {
                true
            } else -> {
                false
            }
        }
    }
}*/