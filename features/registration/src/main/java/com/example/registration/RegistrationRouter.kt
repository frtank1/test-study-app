package com.example.registration

import cafe.adriel.voyager.core.registry.ScreenProvider

//Регистрируем в MyApp
sealed class RegistrationRouter : ScreenProvider {
    object AnyScreen : RegistrationRouter()
}