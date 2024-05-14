package com.brentcodes.fitfamapplication.ui.screens.plan

import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanSchedulePopup(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(10.dp)
    ) {
        val dateState = rememberDatePickerState()
        val openDatePickerDialog = remember { mutableStateOf(false) }
        val timeState = rememberTimePickerState(is24Hour = false)
        val openTimePickerDialog = remember { mutableStateOf(false) }

        Text(text = "Schedule Workout", fontSize = 32.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically){
            Text("Date:", fontWeight = FontWeight.Bold)
            TextButton(onClick = { openDatePickerDialog.value = true }) {
                Text(dateState.selectedDateMillis.toString(), color = Color.LightGray, textDecoration = TextDecoration.Underline)
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically){
            Text("Time:", fontWeight = FontWeight.Bold)
            TextButton(onClick = { openTimePickerDialog.value = true }) {
                Text(timeState.hour.toString(), color = Color.LightGray, textDecoration = TextDecoration.Underline)
            }
        }
        
        if (openDatePickerDialog.value) {
            DatePickerDialog(
                onDismissRequest = {
                    openDatePickerDialog.value = false
                },
                confirmButton = {
                    Button(onClick = { openDatePickerDialog.value = false }) { Text(text = "CONFIRM")}
                },
                dismissButton = {
                    Button(onClick = { openDatePickerDialog.value = false }) { Text(text = "DISMISS")}
                }) {
                DatePicker(state = dateState)
            }
        }
        if (openTimePickerDialog.value) {
            TimePicker(state = timeState)
        }
    }
}