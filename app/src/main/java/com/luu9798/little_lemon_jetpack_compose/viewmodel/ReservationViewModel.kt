package com.luu9798.little_lemon_jetpack_compose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.luu9798.little_lemon_jetpack_compose.model.Reservation
import com.luu9798.little_lemon_jetpack_compose.model.RestaurantLocation

class ReservationViewModel: ViewModel() {

    //Sample restaurants
    val restaurants = listOf(
        RestaurantLocation(
            city = "Las Vegas",
            neighborhood = "Downtown",
            phoneNumber = "(702) 555-9898"
        ),
        RestaurantLocation(
            city = "Los Angeles",
            neighborhood = "North Hollywood",
            phoneNumber = "(213) 555-1453"
        ),
        RestaurantLocation(
            city = "Los Angeles",
            neighborhood = "Venice",
            phoneNumber = "(310) 555-1222"
        ),
        RestaurantLocation(
            city = "Nevada",
            neighborhood = "Venice",
            phoneNumber = "(725) 555-5454"
        ),
        RestaurantLocation(
            city = "San Francisco",
            neighborhood = "North Beach",
            phoneNumber = "(415) 555-1345"
        ),
        RestaurantLocation(
            city = "San Francisco",
            neighborhood = "Union Square",
            phoneNumber = "(415) 555-9813"
        ),
    )

    //UI State
    var selectedRestaurant by mutableStateOf<RestaurantLocation?>(null)
    var reservation by mutableStateOf<Reservation?>(null)

    //UI form field
    var partySize by mutableIntStateOf(1)
    var dateTime by mutableLongStateOf(System.currentTimeMillis())
    var customerName by mutableStateOf("")
    var customerEmail by mutableStateOf("")
    var customerPhone by mutableStateOf("")
    var specialRequest by mutableStateOf("")

    //UI validation error
    var validationError by mutableStateOf<String?>(null)
}