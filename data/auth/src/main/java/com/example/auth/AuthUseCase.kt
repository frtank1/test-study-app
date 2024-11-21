package com.example.auth

interface AuthUseCase {
   suspend fun registerUser(email: String, password: String):Boolean

   suspend fun loginUser(email: String, password: String):Boolean

   suspend fun resetPassword(email: String): Boolean
}