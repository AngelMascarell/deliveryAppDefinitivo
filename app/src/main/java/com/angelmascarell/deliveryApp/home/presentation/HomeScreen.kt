package com.angelmascarell.deliveryApp.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.angelmascarell.deliveryApp.R
import com.angelmascarell.deliveryApp.core.routes.Routes

@Composable
fun HomeScreen(navController: NavHostController, homeViewModel: HomeViewModel) {
    BackgroundArea {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Header()

            Spacer(modifier = Modifier.height(16.dp))
            Body(navController)

            Spacer(modifier = Modifier.height(16.dp))
            Footer()
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
fun Header() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Delivery App", style = MaterialTheme.typography.titleLarge)

        Image(
            painter = painterResource(id = R.drawable.tastylogo),
            contentDescription = "Delivery Icon",
            modifier = Modifier
                .size(108.dp)
                .padding(top = 8.dp)
        )
    }
}

@Composable
fun Body(navController: NavHostController) {
    val restaurantImages = listOf(
        R.drawable.macdonaldsjpg,
        R.drawable.kfc,
        R.drawable.burguer,
        R.drawable.tepuy,
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = "Restaurantes:",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 18.dp)
            )
        }

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
                    onClick = {
                        navController.navigate(Routes.DetailsScreen.route)
                    },
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(8.dp)
                ) {
                    Text("Ver más")
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
fun RestaurantImage(imageResId: Int) {
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(shape = MaterialTheme.shapes.medium),
        contentScale = ContentScale.Crop
    )
}


@Composable
fun Footer() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Shopping Cart")
        Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
    }
}
