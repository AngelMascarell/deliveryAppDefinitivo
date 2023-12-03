package com.angelmascarell.deliveryApp.signin.data.network.response

import com.google.gson.annotations.SerializedName

data class SignInResponse(@SerializedName("access_token") val accesToken:String,
                          @SerializedName("token_type") val tokenType:String,
                          @SerializedName("expires_in") val expiresIn:String,
                          @SerializedName("expires_at") val expiresAt:String,
                          @SerializedName("refresh_token") val refreshToken:String
)
