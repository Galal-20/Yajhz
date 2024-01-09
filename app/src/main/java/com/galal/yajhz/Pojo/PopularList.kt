package com.galal.yajhz.Pojo

data class PopularList(
    val `data`: List<Populars>,
    val message: String,
    val response_code: Int,
    val success: Boolean
)