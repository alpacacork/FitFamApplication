package com.brentcodes.fitfamapplication.ui.screens.plan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.brentcodes.fitfamapplication.ui.theme.BackgroundGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanSchedulePopup(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(BackgroundGray)
    ) {
        val state = rememberDatePickerState()
        val openDialog = remember { mutableStateOf(false) }

        Text(text = "Schedule Workout")
        Button(onClick = { openDialog.value = true }) { Text("Open")}
        if (openDialog.value) {
            DatePickerDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                confirmButton = {
                    Button(onClick = { openDialog.value = false }) { Text(text = "CONFIRM")}
                },
                dismissButton = {
                    Button(onClick = { openDialog.value = false }) { Text(text = "DISMISS")}
                }) {
                DatePicker(state = state)
            }
        }
    }
}