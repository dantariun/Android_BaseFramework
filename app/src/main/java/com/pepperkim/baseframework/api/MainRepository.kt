package com.pepperkim.baseframework.api

import com.pepperkim.baseframework.api.model.Login
import com.pepperkim.baseframework.api.model.common.ApiErrorCode
import com.pepperkim.baseframework.api.model.common.ApiRequestCommon
import com.pepperkim.baseframework.repo.MemStore
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService :ApiService) {

    suspend fun checkSMS(param1: String, param2: String) =
        apiService.onCheckSMS(param1, param2)

    suspend fun login(userId: String?) : String?{
        MemStore.Auth.clearToken()
        val res = apiService.runCatching {
            if (userId.isNullOrBlank())
                login(ApiRequestCommon())
            else
                login(Login.Request(userId))
        }.onFailure {
            Timber.w(it)
        }.getOrNull() ?: return null

        if (res.status != ApiErrorCode.SUCCESS || res.payload?.result_value != Login.Response.ResultValue.Y)
            return null.also { Timber.d("Login Response Failed (${res.status}: ${res.message}") }

        MemStore.Auth.updateAccessToken(res.payload.access_token, res.payload.expires_in)
        MemStore.User.updateUserInfo(res.payload)
        return res.payload.access_token
    }

    suspend fun checkToken(){

    }

    suspend fun searchMovieContent(word:String) =
        apiService.searchMovieContent(word)
}