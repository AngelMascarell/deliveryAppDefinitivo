package com.angelmascarell.deliveryApp.details.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor() : ViewModel() {
    var restaurantName: String? = "KFC"
    var restaurantDescription: String? = "En nuestra página web, te sumergirás en el delicioso mundo de Kentucky Fried Chicken (KFC)," +
            " donde la pasión por el pollo frito se encuentra con sabores auténticos y recetas secretas que perduran desde 1930."
    var restaurantAddress: String? = "Avenida de Grecia, Benidorm, ALicante"
}
