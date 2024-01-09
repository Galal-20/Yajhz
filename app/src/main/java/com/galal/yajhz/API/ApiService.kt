package com.galal.yajhz.API

import com.galal.yajhz.Data.LoginRequest
import com.galal.yajhz.Data.LoginResponse
import com.galal.yajhz.Data.SignUpRequest
import com.galal.yajhz.Data.SignUpResponse
import com.galal.yajhz.Pojo.CategoryList
import com.galal.yajhz.Pojo.PopularList
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
        @POST("login")
        suspend fun loginUser(@Body loginRequest: LoginRequest): LoginResponse

        @POST("client-register")
        suspend fun signUpUser(@Body signUpRequest: SignUpRequest): SignUpResponse


        @GET("home-base-categories")
        fun getCategoriesItem(): Call<CategoryList>

        @GET("trending-sellers?")
        fun getTrendingList(): Call<PopularList>

        @GET("popular-sellers?")
        fun getPopularList(): Call<PopularList>
}