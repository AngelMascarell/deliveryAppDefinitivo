package com.angelmascarell.deliveryApp.signin.domain

import com.angelmascarell.deliveryApp.core.security.PasswordHash
import com.angelmascarell.deliveryApp.signin.data.SignInRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val networkRepository: SignInRepository,
    private val cryptoHash: PasswordHash
) {

    suspend operator fun invoke(user: String, password: String):Boolean =
        networkRepository.doSignIn(user, password)
}