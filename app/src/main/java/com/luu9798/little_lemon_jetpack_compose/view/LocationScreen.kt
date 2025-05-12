package com.luu9798.little_lemon_jetpack_compose.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.luu9798.little_lemon_jetpack_compose.model.RestaurantLocation
import com.luu9798.little_lemon_jetpack_compose.viewmodel.ReservationViewModel

@Composable
fun LocationScreen(
    viewModel: ReservationViewModel,
    onSelect: (RestaurantLocation) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        //LittleLemonLogo()
        Text(
            text = "Select a location",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(Color.LightGray, RoundedCornerShape(8.dp))
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
        LazyColumn {
            items(viewModel.restaurants) { location ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable{ onSelect(location) }
                ) {
                    RestaurantItem(location)
                }
            }
        }
    }
}

@Composable
fun RestaurantItem(location: RestaurantLocation) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = location.city, style = MaterialTheme.typography.titleMedium)
        Text(text = "${location.neighborhood} – ${location.phoneNumber}",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray)
    }
}