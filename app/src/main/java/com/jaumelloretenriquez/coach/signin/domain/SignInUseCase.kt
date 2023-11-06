package com.jaumelloretenriquez.coach.signin.domain

import com.jaumelloretenriquez.coach.signin.data.SignInRepository

class SignInUseCase {
    private val networkRepository = SignInRepository()

    suspend operator fun invoke(user: String, password: String):Boolean =
        networkRepository.doSignIn(user, password)
}