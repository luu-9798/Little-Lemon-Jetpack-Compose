package com.luu9798.little_lemon_jetpack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.luu9798.little_lemon_jetpack_compose.model.Reservation
import com.luu9798.little_lemon_jetpack_compose.model.RestaurantLocation
import com.luu9798.little_lemon_jetpack_compose.view.LocationScreen
import com.luu9798.little_lemon_jetpack_compose.view.ReservationDetailScreen
import com.luu9798.little_lemon_jetpack_compose.view.ReservationFormScreen
import com.luu9798.little_lemon_jetpack_compose.viewmodel.ReservationViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: ReservationViewModel = viewModel()
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "locations"
            ) {
                composable("locations") {
                    LocationScreen(viewModel) { restaurant ->
                        viewModel.selectedRestaurant = restaurant
                        navController.navigate("form")
                    }
                }
                composable("form") {
                    ReservationFormScreen(viewModel) {
                        viewModel.validateAndSubmit()
                        if (viewModel.validationError == null) {
                            navController.navigate("details")
                        }
                    }
                }
                composable("details") {
                    ReservationDetailScreen(
                        reservation =
                            viewModel.reservation ?:
                            Reservation(
                                restaurantLocation = RestaurantLocation(
                                    city = "Unknown",
                                    neighborhood = "Unknown",
                                    phoneNumber = "Unknown"
                                ),
                                customerName = "Unknown",
                                customerEmail = "Unknown",
                                customerPhone = "Unknown",
                                dateTime = System.currentTimeMillis(),
                                partySize = 1,
                                specialRequest = "Unknown"
                            )
                    )
                }
            }
            /*LittleLemonJetpackComposeTheme {

            }*/
        }
    }
}