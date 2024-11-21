package com.example.test_study_app.di

import com.example.registration.RegistrationViewModel
import kz.secret_santa_jusan.presentation.Auth.AuthViewModel
import org.koin.dsl.module

val featureViewModel = module {
    factory { AuthViewModel(get()) }
    factory { RegistrationViewModel(get()) }
}
