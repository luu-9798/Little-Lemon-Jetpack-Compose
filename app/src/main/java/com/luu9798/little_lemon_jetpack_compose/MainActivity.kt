package com.luu9798.little_lemon_jetpack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.luu9798.little_lemon_jetpack_compose.view.LocationScreen
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
                    /*ReservationFormScreen(vm) {
                        vm.validateAndSubmit()
                        if (vm.validationError == null) {
                            nav.navigate("details")
                        }
                    }*/
                }
                composable("details") {
                    //ReservationDetailScreen(vm.reservation!!)
                }
            }
            /*LittleLemonJetpackComposeTheme {

            }*/
        }
    }
}