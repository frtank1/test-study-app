package com.example.test_study_app.di

import com.example.auth.AuthUseCase
import com.example.auth.AuthUseCaseImpl
import org.koin.dsl.module

val authModul = module{
    factory<AuthUseCase> { AuthUseCaseImpl(get()) }
}