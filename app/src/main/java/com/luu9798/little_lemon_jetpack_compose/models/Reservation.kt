package com.luu9798.little_lemon_jetpack_compose.models

//User reservations
data class Reservation (
    val restaurantLocation: RestaurantLocation,
    val customerName: String,
    val customerEmail: String,
    val customerPhone: String,
    val dateTime: Long,
    val partySize: Int,
    val specialRequest: String
)