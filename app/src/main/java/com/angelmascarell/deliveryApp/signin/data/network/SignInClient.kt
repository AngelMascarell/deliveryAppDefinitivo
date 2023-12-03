package com.angelmascarell.deliveryApp.signin.data.network

import com.angelmascarell.deliveryApp.signin.data.network.dto.UserDTO
import com.angelmascarell.deliveryApp.signin.data.network.response.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignInClient {

    @Headers("apikey: " +
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3" +
            "MiOiJzdXBhYmFzZSIsInJlZiI6ImNsdXRydHFjZ" +
            "WVyZ2FmZHRxamViIiwicm9sZSI6ImFub24iLC" +
            "JpYXQiOjE3MDAwNTI2MDksImV4cCI6MjAxNT" +
            "YyODYwOX0.M0LOr8aHu4GA8TE9B-7skWZl348m2YF1ACUX_CZQcBw", "Content-Type: application/json")
    @POST("auth/v1/token?grant_type=password")
    suspend fun doSignIn(@Body user: UserDTO):Response<SignInResponse>
}

