package com.example.auth

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await


class AuthUseCaseImpl(private val firebaseAuth: FirebaseAuth) : AuthUseCase {

    override suspend fun registerUser(email: String, password: String): Boolean {
        var result = false
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    Log.d(task.toString(), "isSuccessful")
                    result = true
                } else {
                    Log.d(task.toString(), "Error")
                    result = false
                }
            }
        return result
    }

    override suspend fun loginUser(email: String, password: String):Boolean {
        var result = false
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    Log.d(task.toString(), "isSuccessful")
                    result = true
                } else {
                    Log.d(task.toString(), "Error")
                    result = false
                }
            }
        return result
    }

    override suspend fun resetPassword(email: String): Boolean {
        return try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            Log.d("Auth", "Password reset email sent to $email")
            true
        } catch (e: Exception) {
            Log.e("Auth", "Error sending reset email: ${e.message}")
            false
        }
    }
}
