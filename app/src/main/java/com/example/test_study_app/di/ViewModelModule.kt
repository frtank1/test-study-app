package com.example.test_study_app.di

import kz.secret_santa_jusan.presentation.Auth.AuthViewModel
import org.koin.dsl.module

val featureAuthViewModel = module {
    factory { AuthViewModel() }
}