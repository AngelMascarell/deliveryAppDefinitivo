package com.angelmascarell.deliveryApp.details.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.angelmascarell.deliveryApp.R
import com.angelmascarell.deliveryApp.core.routes.Routes
import com.angelmascarell.deliveryApp.ui.theme.BackgroundArea

@Composable
fun RestaurantDetailsScreen(navController: NavHostController, detailsViewModel: DetailsViewModel) {
    BackgroundArea {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            RestaurantHeader()

            Spacer(modifier = Modifier.height(10.dp))
            RestaurantBody(detailsViewModel)

            Spacer(modifier = Modifier.height(8.dp))
            Footer(navController)
        }
    }
}

@Composable
fun BackgroundArea(content: @Composable (Modifier) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.banerfondonaranja),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        content(Modifier)
    }
}

@Composable
fun RestaurantBody(restaurantDetailsViewModel: DetailsViewModel) {
    val restaurantName = restaurantDetailsViewModel.restaurantName
    val restaurantDescription = restaurantDetailsViewModel.restaurantDescription
    val restaurantAddress = restaurantDetailsViewModel.restaurantAddress

    val restaurantImages = listOf(
        R.drawable.kfc5,
        R.drawable.kfc2,
    )


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .fillMaxHeight(0.9f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .clip(MaterialTheme.shapes.medium)
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Section(
                    "Descripción del restaurante",
                    restaurantDescription ?: "Sin descripción disponible"
                )

                Spacer(modifier = Modifier.height(16.dp))

                Section("Dirección", restaurantAddress ?: "Sin dirección disponible")

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Menús disponibles:",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 18.dp)
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    items(restaurantImages) { imageResId ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .clip(shape = MaterialTheme.shapes.medium)
                        ) {
                            Image(
                                painter = painterResource(id = imageResId),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(shape = MaterialTheme.shapes.medium),
                                contentScale = ContentScale.Crop
                            )

                            Button(
                                onClick = {  },
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(8.dp)
                            ) {
                                Text("Ver más")
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun Section(title: String, content: String) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(text = content)
    }
}

@Composable
fun RestaurantHeader() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.kfcheader),
            contentDescription = "Restaurant Image",
            modifier = Modifier
                .size(120.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.primary)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "KFC RESTAURANT",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}


@Composable
fun Footer(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                navController.navigate(Routes.HomeScreen.route)
            }
        ) {
            Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
        }

        IconButton(
            onClick = {
                navController.navigate(Routes.HomeScreen.route)
            }
        ) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        }

        IconButton(
            onClick = {
                navController.navigate(Routes.HomeScreen.route)
            }
        ) {
            Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Shopping Cart")
        }

        IconButton(
            onClick = {
                navController.navigate(Routes.HomeScreen.route)
            }
        ) {
            Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
        }
    }
}