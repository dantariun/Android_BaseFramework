package com.pepperkim.baseframework.api

import com.google.gson.annotations.SerializedName

data class CommonResponse(
    @SerializedName("code") val code: String = "",
    @SerializedName("message") val message: String = ""
)
