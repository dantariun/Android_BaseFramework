package com.pepperkim.baseframework.repo.mem

import android.os.Build
import android.os.SystemClock
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import timber.log.Timber
import java.util.*

class Auth {
    var accessToken: String = "KakaoAK 813f3ccfbb3d0d9b806668dc576f9c1f"
        private set
    var accessTokenExpired: Long = -1
        private set

    fun clearToken() {
        accessToken = ""
        accessTokenExpired = -1
    }

    fun isTokenExpired(spareTimeMS: Long = 0) = SystemClock.elapsedRealtime() >= (accessTokenExpired - spareTimeMS)

    fun updateAccessToken(token: String, tokenDuration: Int) {
        accessToken = token
        accessTokenExpired = SystemClock.elapsedRealtime() + tokenDuration * 1000
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateAccessTokenAtJWT(jwt: String): Boolean {
        data class JWTClaims(val exp: Long, val iat: Long)

        val encClaim = jwt.split('.').dropLast(1).lastOrNull() ?: return false
        val claims = encClaim.runCatching {
            Gson().fromJson(Base64.getDecoder().decode(this).decodeToString(), JWTClaims::class.java)
        }.onFailure(Timber::wtf).getOrNull()
            ?: return false

        // client 시간을 보장할 수 없기에 boot time 기준으로 유효시간을 계산한다.
        val effectiveTime = (claims.exp - claims.iat) * 1000
        accessTokenExpired = SystemClock.elapsedRealtime() + effectiveTime
        accessToken = jwt

        return true
    }
}