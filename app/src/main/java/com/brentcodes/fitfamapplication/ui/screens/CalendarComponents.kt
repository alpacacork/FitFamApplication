package com.brentcodes.fitfamapplication.ui.screens

import android.inputmethodservice.Keyboard
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brentcodes.fitfamapplication.ui.theme.BackgroundGray
import com.brentcodes.fitfamapplication.ui.theme.DarkerGray
import com.brentcodes.fitfamapplication.ui.theme.RedAccent
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.WeekDay
import java.time.DayOfWeek
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun DateBoxWeek(
    day : WeekDay,
    current : Boolean,
    selected: Boolean,
    workedOut : Boolean = false,
    onClick : (WeekDay) -> Unit = {}
) {
    val bgColor = if (current) RedAccent else BackgroundGray
    val border = if (selected) Color.White else Color.Transparent

    Box(
        modifier = Modifier
            .size(50.dp)
            .padding(5.dp)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(5.dp)
            )
            .border(BorderStroke(2.dp, border))
//            .clip(RoundedCornerShape(5.dp))
            .background(bgColor)
            .clickable(
                onClick = { onClick(day) }
            )
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            modifier = Modifier.align(Alignment.Center),
            color = Color.White,
            fontSize = 16.sp
        )
        if (workedOut) Box(
            modifier = Modifier.align(Alignment.TopEnd)
                .padding(5.dp)
                .size(5.dp)
                .background(Color.White, RoundedCornerShape(50))
        )
    }
}

@Composable
fun DateBoxMonth(
    day : CalendarDay,
    current : Boolean = false,
    selected: Boolean = false,
    workedOut : Boolean = false,
    includeInOutDays: Boolean = true,
    onClick : (CalendarDay) -> Unit = {}
) {
    val isInOutDay: Boolean = day.position != DayPosition.MonthDate
    val displayDateBox = includeInOutDays or !isInOutDay

    val bgColor = if (current) RedAccent else if (!isInOutDay) BackgroundGray else Color.DarkGray.copy(alpha = 0.5f)
    val txtColor = if (!isInOutDay) Color.White else Color.LightGray
    val border = if (selected) Color.White else Color.Transparent

    if (displayDateBox) Box(
        modifier = Modifier
            .size(50.dp)
            .padding(5.dp)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(5.dp)
            )
            .border(BorderStroke(2.dp, border))
            .background(bgColor)
            .clickable(
                onClick = { onClick(day) }
            )
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            modifier = Modifier.align(Alignment.Center),
            color = txtColor,
            fontSize = 16.sp
        )
        if (workedOut) Box(
            modifier = Modifier.align(Alignment.TopEnd)
                .padding(5.dp)
                .size(5.dp)
                .background(Color.White, RoundedCornerShape(50))
        )
    }
}

@Composable
fun CalendarMonthHeader(modifier: Modifier = Modifier, daysOfWeek: List<DayOfWeek>, month: CalendarMonth) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = month.yearMonth.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(DarkerGray)
        ) {
            for (dayOfWeek in daysOfWeek) {
                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    color = Color.White,
                    text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }

}