package kz.secret_santa_jusan.presentation.Auth

import cafe.adriel.voyager.core.registry.ScreenProvider

//Регистрируем в MyApp
sealed class AuthRouter : ScreenProvider {
    object AnyScreen : AuthRouter()
}