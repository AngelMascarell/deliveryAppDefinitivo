package com.angelmascarell.deliveryApp.core.routes

sealed class Routes(val route: String) {
    object  SignInScreen: com.angelmascarell.deliveryApp.core.routes.Routes("signInScreen")
    object  SignUpScreen: com.angelmascarell.deliveryApp.core.routes.Routes("signUpScreen")
    object  HomeScreen: com.angelmascarell.deliveryApp.core.routes.Routes("homeScreen")
    object  DetailsScreen: com.angelmascarell.deliveryApp.core.routes.Routes("detailsScreen")

}



//{}  fun createRoute(id:Int, username: String) = "homeScreen/$id?username=$username"