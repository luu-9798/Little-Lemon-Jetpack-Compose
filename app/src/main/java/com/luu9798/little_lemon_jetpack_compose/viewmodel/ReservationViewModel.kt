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

    //Validate and submit
    fun validateAndSubmit() {
        //Customer name must be at least 3 characters long and contain only letters and spaces
        val nameValid = customerName.trim().length >= 3 && customerName.all { it.isLetter() || it.isWhitespace() }

        //Customer email must be a valid email address
        val emailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(customerEmail).matches()

        if (!nameValid || !emailValid || customerPhone.isBlank()) {
            //Show all the error messages by joining them with a new line
            val messages = mutableListOf<String>()
            if (!nameValid) messages.add("Customer name must be at least 3 characters long")
            if (!emailValid) messages.add("Customer email must be a valid email address")
            if (customerPhone.isBlank()) messages.add("Customer phone cannot be empty")
            validationError = messages.joinToString("\n")
            return
        }

        //clean error
        validationError = null

        //build reservation
        selectedRestaurant?.let { location ->
            reservation = Reservation(
                restaurantLocation = location,
                partySize = partySize.coerceAtLeast(1),
                dateTime = dateTime,
                customerName = customerName.trim(),
                customerEmail = customerEmail.trim(),
                customerPhone = customerPhone.trim(),
                specialRequest = specialRequest.trim()
            )
        }
    }
}