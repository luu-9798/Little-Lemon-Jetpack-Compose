package com.luu9798.little_lemon_jetpack_compose.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.luu9798.little_lemon_jetpack_compose.model.Reservation
import java.util.Date

@Composable
fun ReservationDetailScreen(reservation: Reservation) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LittleLemonLogo()
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "RESERVATION",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column {
            Text(
                text = "Restaurant:",
                fontWeight = FontWeight.Bold
            )
            RestaurantItem(reservation.restaurantLocation)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Name: ${reservation.customerName}")
        Text("Email: ${reservation.customerEmail}")
        Text("Phone: ${reservation.customerPhone}")
        Text("Party Size: ${reservation.partySize}")
        Text("Date and Time: ${java.text.DateFormat.getDateTimeInstance().format(Date(reservation.dateTime))}")
        if (reservation.specialRequest.isNotBlank()) {
            Spacer(Modifier.height(16.dp))
            Text("Special requests:", fontWeight = FontWeight.Bold)
            Text(reservation.specialRequest)
        }
    }
}