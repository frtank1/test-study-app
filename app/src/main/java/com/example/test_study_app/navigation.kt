package com.example.test_study_app

import cafe.adriel.voyager.core.registry.screenModule
import com.example.registration.RegistrationRouter
import com.example.registration.RegistrationScreen
import kz.secret_santa_jusan.presentation.Auth.AuthRouter
import kz.secret_santa_jusan.presentation.Auth.AuthScreen

val featureAuth = screenModule {
    register<AuthRouter.AnyScreen> {
        AuthScreen()
    }
}

val featureReg = screenModule {
    register<RegistrationRouter.AnyScreen> {
        RegistrationScreen()
    }
}