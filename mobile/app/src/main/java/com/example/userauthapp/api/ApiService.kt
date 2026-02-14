package com.example.userauthapp.api

import com.example.userauthapp.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/auth/register") // Make sure "api/auth/" is included here!
    fun register(@Body user: User): Call<String>

    @POST("api/auth/login")
    fun login(@Body user: User): Call<String>
}