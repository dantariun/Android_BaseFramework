package com.pepperkim.baseframework.api.model.common

import com.google.gson.TypeAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

data class ApiResponse<T>(
	val systemType: String?,
	@JsonAdapter(StatusAdapter::class)
	val status: ApiErrorCode?,
	val message: String?,
	val payload: T?
) {

	class StatusAdapter : TypeAdapter<ApiErrorCode>() {
		override fun write(out: JsonWriter, value: ApiErrorCode) {
			out.value(value.data)
		}

		override fun read(`in`: JsonReader): ApiErrorCode {
			return ApiErrorCode.dataOf(`in`.nextInt())
		}
	}
}