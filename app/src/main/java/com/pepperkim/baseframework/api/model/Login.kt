package com.pepperkim.baseframework.api.model

import com.pepperkim.baseframework.api.model.common.ApiRequestCommon

class Login {
    data class Request(val userId: String): ApiRequestCommon()

    data class Response(
        val access_token: String,
        val userNm: String,
        val result_value: ResultValue,
        val userId: String,
        val expires_in: Int,
        val robotUserSeq: Long
    ) {
        enum class ResultValue {
            Y, N
        }
    }
}