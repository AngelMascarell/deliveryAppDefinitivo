package com.angelmascarell.deliveryApp.signin.data

import com.angelmascarell.deliveryApp.signin.data.network.SignInService
import javax.inject.Inject

class SignInRepository @Inject constructor(private val api: SignInService) {

    suspend fun doSignIn(user: String, password: String): Boolean = api.doSignIn(user, password)
}