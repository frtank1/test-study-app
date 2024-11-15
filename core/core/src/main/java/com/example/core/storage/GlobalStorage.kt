package com.example.core.storage

import com.example.core.network.model.TokenModel
import io.paperdb.Paper


class GlobalStorage {
    companion object {
        private val TOKEN = "token"
        private val PASSWORD = "password"
        private val keys = arrayListOf(TOKEN)

        var applicationId: String = ""

        private var _access_token: String? = null
        private var _password: String? = null
        val access_token: String get() {
            if(_access_token == null) getAuthToken()
            return _access_token?:""
        }

        private var _refresh_token: String? = null
        val refresh_token: String get() {
            if(_refresh_token == null) getAuthToken()
            return _refresh_token?:""
        }
        fun logOut() {
            _access_token = null
            _refresh_token = null
            keys.forEach { key ->
                Paper.book().delete(key)
            }
            Paper.book().delete("USER")
        }



        fun saveAuthToken(access_token: String, refresh_token: String){
            _access_token = access_token
            _refresh_token = refresh_token
            Paper.book().write(TOKEN, TokenModel(access_token, refresh_token))
        }

        fun savePassword(password: String){
            _password = access_token
            Paper.book().write(PASSWORD, password)
        }

        fun getAuthToken(): TokenModel?{
            val token = Paper.book().read<TokenModel>(TOKEN, null)
            _access_token = token?.access_token
            _refresh_token = token?.refresh_token
            return token
        }
        fun getPassword(): String?{
            val password = Paper.book().read<String>(PASSWORD, null)
            return password
        }


        fun setBaseUrl(base_url: String){
            Paper.book().write("base_url", base_url)
        }

        val BASE_URL: String get() {
            return Paper.book().read<String>("base_url", "")!!
        }
    }
}