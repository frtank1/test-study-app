package com.example.core.network

import com.example.core.network.model.TokenModel
import com.example.core.storage.GlobalStorage
import com.example.core.views.CoreApp
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import trikita.log.Log

@OptIn(InternalAPI::class)
val httpClientModule = module {
    single {
        HttpClient(Android).config {
            defaultRequest {
                url("https://stepik/")
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.i("HttpClient", message)
                    }
                }
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    explicitNulls = true
                })
            }
            install (Auth) {
                Log.d("access_token install Auth", GlobalStorage.access_token)
                bearer {
                    Log.d("access_token bearer", GlobalStorage.access_token)
                    loadTokens {
                        Log.d("access_token loadTokens", GlobalStorage.access_token)
                        BearerTokens(
                            GlobalStorage.access_token,
                            GlobalStorage.refresh_token
                        )
                    }
                    sendWithoutRequest { request ->
                        !request.url.toString().contains("/auth/login") &&
                                !request.url.toString().contains("/auth/forgot-password")
                    }
                    refreshTokens {
                        val response = client.post(GlobalStorage.BASE_URL + "/refresh-token") {
                            headers {
                                append(
                                    "Authorization",
                                    "Bearer " + GlobalStorage.refresh_token
                                )
                            }
                            body = FormDataContent(Parameters.build {
                                append(
                                    "refresh_token",
                                    GlobalStorage.getAuthToken()?.refresh_token ?: ""
                                )
                            })
                        }
                        Log.d("status refresh", response.status.value)

                        if (response.status.value >= 200 && response.status.value < 300) {
                            val token = response.body<TokenModel>()
                            GlobalStorage.saveAuthToken(
                                token.access_token,
                                token.refresh_token
                            )
                            BearerTokens(
                                accessToken = token.access_token,
                                refreshToken = token.refresh_token
                            )
                        } else {
                            CoreApp.logOut(true)
                            throw RuntimeException("failed to refresh tokens")
                        }
                    }
                }
            }

        }
    }
}