package com.angelmascarell.deliveryApp

import SignUpViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.angelmascarell.deliveryApp.core.routes.Routes
import com.angelmascarell.deliveryApp.details.presentation.DetailsViewModel
import com.angelmascarell.deliveryApp.details.presentation.RestaurantDetailsScreen
import com.angelmascarell.deliveryApp.home.presentation.HomeScreen
import com.angelmascarell.deliveryApp.home.presentation.HomeViewModel
import com.angelmascarell.deliveryApp.signin.presentation.SignInScreen
import com.angelmascarell.deliveryApp.signin.presentation.SignInViewModel
import com.angelmascarell.deliveryApp.signup.presentation.SignUpScreen
import com.angelmascarell.deliveryApp.ui.theme.BackgroundArea
import com.angelmascarell.deliveryApp.ui.theme.BackgroundReverse
import com.angelmascarell.deliveryApp.ui.theme.DeliveryAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor() : ComponentActivity() {
    private val signInViewModel: SignInViewModel by viewModels()
    private val signUpViewModel: SignUpViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeliveryAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Routes.SignInScreen.route) {
                        composable(Routes.SignInScreen.route) {
                            BackgroundArea()
                            SignInScreen(navController, signInViewModel = signInViewModel)
                        }
                        composable(Routes.SignUpScreen.route) {
                            BackgroundReverse()
                            SignUpScreen(navController, signUpViewModel)
                        }
                        composable(Routes.HomeScreen.route) {
                            BackgroundArea()
                            HomeScreen(navController, homeViewModel)
                        }
                        composable(Routes.DetailsScreen.route) {
                            RestaurantDetailsScreen(navController, detailsViewModel)
                        }
                    }
                }
            }
        }
    }
}