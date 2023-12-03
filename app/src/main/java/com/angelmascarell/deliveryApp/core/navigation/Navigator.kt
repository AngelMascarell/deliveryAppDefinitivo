package com.angelmascarell.deliveryApp.core.navigation

import androidx.compose.runtime.Composable
import com.angelmascarell.deliveryApp.core.routes.Routes
import com.angelmascarell.deliveryApp.home.presentation.HomeScreen
import com.angelmascarell.deliveryApp.signin.presentation.SignInViewModel

import javax.inject.Inject

class Navigator @Inject constructor(
    private val signInViewModel: SignInViewModel,
    private val homeScreen: Routes.HomeScreen
) {

    @Composable
    operator fun invoke() {

    }
}