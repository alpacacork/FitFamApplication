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
import androidx.compose.material3.TimeInput
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
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

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

        //Instant.ofEpochMilli(monthJoinedTimestamp?: 1).atZone(ZoneId.systemDefault())
        val dateSelected = if (dateState.selectedDateMillis == null) "Unselected" else Instant.ofEpochMilli(dateState.selectedDateMillis!!).atZone(
            ZoneId.systemDefault()).format(DateTimeFormatter.ISO_LOCAL_DATE)
        val timeMinutes = timeState.minute
        val timeHours = timeState.hour

        Text(text = "Schedule Workout", fontSize = 32.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically){
            Text("Date:", fontWeight = FontWeight.Bold)
            TextButton(onClick = { openDatePickerDialog.value = true }) {
                Text(dateSelected, color = Color.LightGray, textDecoration = TextDecoration.Underline)
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically){
            Text("Time:", fontWeight = FontWeight.Bold)
            TextButton(onClick = { openTimePickerDialog.value = true }) {
                Text(text = String.format("%02d:%02d", timeHours, timeMinutes), color = Color.LightGray, textDecoration = TextDecoration.Underline)
            }
        }
        
        if (openDatePickerDialog.value) {
            DatePickerDialog(
                modifier = Modifier.padding(10.dp),
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
            DatePickerDialog(
                modifier = Modifier.padding(20.dp),
                onDismissRequest = { openTimePickerDialog.value = false} ,
                confirmButton = { Button(onClick = { openTimePickerDialog.value = false }) { Text("CONFIRM") } },
                dismissButton = { Button(onClick = { openTimePickerDialog.value = false }) { Text("CANCEL") } }
            ) {
                TimeInput(state = timeState, modifier = Modifier.padding(20.dp))
            }

        }
    }
}