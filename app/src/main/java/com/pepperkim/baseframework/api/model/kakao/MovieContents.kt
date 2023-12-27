package com.pepperkim.baseframework.api.model.kakao

data class MovieContents(
    val meta : ResponseMeta?,
    val documents:List<Movie>?
)

data class ResponseMeta(
    val total_count:Long,
    val pageable_count:Long,
    val is_end:Boolean
)

data class Movie(
    val title:String,
    val play_time:Long,
    val thumbnail:String,
    val url:String,
    val datetime:String,
    val author:String
)
