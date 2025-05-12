package com.luu9798.little_lemon_jetpack_compose.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.text.DateFormat
import android.icu.util.Calendar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.luu9798.little_lemon_jetpack_compose.viewmodel.ReservationViewModel
import java.util.Date

@Composable
fun ReservationFormScreen(
    viewModel: ReservationViewModel,
    onConfirm: () -> Unit
) {
    val context = LocalContext.current

    //flags to show date and time picker
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    // break your stored epoch into calendar fields
    val calendar = Calendar.getInstance().also { it.timeInMillis = viewModel.dateTime }
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

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
        OutlinedTextField(
            value = DateFormat.getDateInstance().format(Date(viewModel.dateTime)),
            onValueChange = {/** read only */},
            label = { Text("Date") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
                .clickable { showDatePicker = true }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = DateFormat.getTimeInstance(DateFormat.SHORT).format(Date(viewModel.dateTime)),
            onValueChange = {/** read only */},
            label = { Text("Time") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
                .clickable { showTimePicker = true }
        )

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

        if (showDatePicker) {
            DatePickerDialog(
                context,
                { _, selYear, selMonth, selDay ->
                    // preserve existing time
                    val updatedCal = Calendar.getInstance().apply {
                        set(selYear, selMonth, selDay, hour, minute)
                    }
                    viewModel.dateTime = updatedCal.timeInMillis
                    showDatePicker = false
                },
                year, month, day
            ).show()
        }

        if (showTimePicker) {
            TimePickerDialog(
                context,
                { _, selHour, selMinute ->
                    // preserve selected date
                    val updatedCal = Calendar.getInstance().apply {
                        set(year, month, day, selHour, selMinute)
                    }
                    viewModel.dateTime = updatedCal.timeInMillis
                    showTimePicker = false
                },
                hour, minute, true  // 24-hour clock; use false for AM/PM
            ).show()
        }
    }
}