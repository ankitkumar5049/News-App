package com.practise.newsapp.presentation.screens.register.enterOTP

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.practise.newsapp.common.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OtpVerificationViewModel @Inject constructor(): BaseViewModel() {
    lateinit var storedVerificationId: String

    fun verifyOtp(otp: String) {
        val credential = PhoneAuthProvider.getCredential(storedVerificationId, otp)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    println("User signed in successfully!")
                } else {
                    println("Error: ${task.exception?.message}")
                }
            }
    }
}