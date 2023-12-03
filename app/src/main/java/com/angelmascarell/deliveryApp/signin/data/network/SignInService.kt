package com.angelmascarell.deliveryApp.signin.data.network

import com.angelmascarell.deliveryApp.core.security.PasswordHash
import com.angelmascarell.deliveryApp.signin.data.network.dto.UserDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class SignInService @Inject constructor(
    private val client: SignInClient,
    private val crypto: PasswordHash
) {

    suspend fun doSignIn(user: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            val (hash, salt) = crypto.getCryptoPassword(password)
            val response = client.doSignIn(UserDTO(user, password))

            !response.body()?.accesToken.isNullOrEmpty()
        }
    }
}