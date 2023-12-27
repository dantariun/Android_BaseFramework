package com.pepperkim.baseframework.api

import com.pepperkim.baseframework.api.model.Login
import com.pepperkim.baseframework.api.model.common.ApiRequestCommon
import com.pepperkim.baseframework.api.model.common.ApiResponse
import com.pepperkim.baseframework.api.model.kakao.MovieContents
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("actCheckSms")
    suspend fun onCheckSMS(
        @Field("telno") num: String, @Field("randomNumber") randNumber: String
    ): Response<CommonResponse>

    @GET("v2/search/vclip")
    suspend fun searchMovieContent(@Query("query") word:String):Response<MovieContents>

    @POST("LoginApi")
    suspend fun login(@Body req: ApiRequestCommon): ApiResponse<Login.Response>
}