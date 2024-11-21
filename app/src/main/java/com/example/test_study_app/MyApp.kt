package com.example.test_study_app

import cafe.adriel.voyager.core.registry.ScreenRegistry
import com.example.core.firbase.firebaseModule
import com.example.core.network.httpClientModule
import com.example.core.views.CoreApp
import com.example.test_study_app.di.authModul
import com.example.test_study_app.di.featureViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MyApp : CoreApp() {

    override fun onCreate() {
        super.onCreate()

        ScreenRegistry{
            featureAuth
        }
        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(httpClientModule)
            modules(featureViewModel)
            modules(firebaseModule)
            modules(authModul)
        }
    }
}