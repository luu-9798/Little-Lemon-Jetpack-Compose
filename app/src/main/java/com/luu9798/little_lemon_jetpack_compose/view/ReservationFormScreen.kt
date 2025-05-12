package com.luu9798.little_lemon_jetpack_compose.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.luu9798.little_lemon_jetpack_compose.viewmodel.ReservationViewModel

@Composable
fun ReservationFormScreen(
    viewModel: ReservationViewModel,
    onConfirm: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        //Restaurant Item View
        viewModel.selectedRestaurant?.let {
            RestaurantItem(it)
        }

        Spacer(modifier = Modifier.height(16.dp))

        //Party size
        OutlinedTextField(
            value = viewModel.partySize.toString(),
            onValueChange = {
                viewModel.partySize = it.toIntOrNull() ?: 0
            },
            label = { Text("Party Size") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        //Date and Time picker
        //TODO: Add date and time picker

        Spacer(modifier = Modifier.height(16.dp))

        //Customer name
        OutlinedTextField(
            value = viewModel.customerName,
            onValueChange = { viewModel.customerName = it },
            label = { Text("Name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        //Customer email
        OutlinedTextField(
            value = viewModel.customerEmail,
            onValueChange = { viewModel.customerEmail = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        //Customer phone
        OutlinedTextField(
            value = viewModel.customerPhone,
            onValueChange = { viewModel.customerPhone = it },
            label = { Text("Phone") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )

        Spacer(modifier = Modifier.height(16.dp))

        //Special request
        OutlinedTextField(
            value = viewModel.specialRequest,
            onValueChange = { viewModel.specialRequest = it },
            label = { Text("Special Request (optional)") },
            singleLine = false,
            maxLines = 5
        )

        Spacer(modifier = Modifier.height(16.dp))

        //Confirm button
        Button(
            onClick = onConfirm,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Confirm Reservation")
        }

        //Show error dialog
        viewModel.validationError?.let {
            AlertDialog(
                onDismissRequest = { viewModel.validationError = null },
                title = { Text("Error") },
                text = { Text(it) },
                confirmButton = {
                    Button(onClick = { viewModel.validationError = null }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}