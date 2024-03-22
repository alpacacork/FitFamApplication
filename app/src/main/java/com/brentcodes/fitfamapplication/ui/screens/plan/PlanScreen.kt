package com.brentcodes.fitfamapplication.ui.screens.plan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.ColorUtils
import com.brentcodes.fitfamapplication.ui.screens.CalendarMonthHeader
import com.brentcodes.fitfamapplication.ui.screens.DateBoxMonth
import com.brentcodes.fitfamapplication.ui.theme.BackgroundGray
import com.brentcodes.fitfamapplication.ui.theme.DarkerGray
import com.brentcodes.fitfamapplication.ui.theme.RedAccent
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.VerticalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.OutDateStyle
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.core.yearMonth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PlanScreen() {

    //Calendar at top? Allow users to select a date and add a plan?
    var selection by remember {
        mutableStateOf<CalendarDay?>(null)
    }

    val currentDate = remember { LocalDate.now() }
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(12) }
    val endMonth = remember { currentMonth.plusMonths(12) }
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }
    val daysOfWeek = remember { daysOfWeek() }

    val stateOfCalendar = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = firstDayOfWeek,
        outDateStyle = OutDateStyle.EndOfGrid
    )

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Column {
        Column(
            modifier = Modifier
                .background(DarkerGray)
        ) {
            HorizontalCalendar(
                calendarScrollPaged = true,
                contentPadding = PaddingValues(5.dp),
                state = stateOfCalendar,
                monthHeader = {
                    CalendarMonthHeader(daysOfWeek = daysOfWeek, month = it)
                },
                dayContent = {day ->
                    DateBoxMonth(day, current = day.date==currentDate, selected = day.date == selection?.date, includeInOutDays = true) { clicked ->
                        selection = if (selection == clicked) null else clicked
                        if (clicked.position != DayPosition.MonthDate) {
                            coroutineScope.launch { stateOfCalendar.animateScrollToMonth(clicked.date.yearMonth) }

                        }
                    }
                },
                monthBody = { _, container ->
                    Box(modifier = Modifier.background(DarkerGray)) {
                        container()
                    }
                }
            )
        }
        Box {
            LazyColumn(
                modifier = Modifier
                    .background(BackgroundGray)
                    .fillMaxSize()
            ) {
                if (selection?.date?.dayOfMonth?.rem(2) == (0 ?: false)) {
                    items(10) {
                        val colors = listOf(
                            Color.Red,
                            Color.Gray,
                            Color.Green,
                            Color.Blue,
                            Color.Magenta,
                            Color.Yellow
                        )
                        PlanCard(color = colors.random())
                    }
                } else {
                    item { Text("No scheduled workouts!") }
                }
            }
            FloatingActionButton(
                onClick = { showBottomSheet = true },
                containerColor = RedAccent,
                contentColor = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(10.dp)
                ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add Plan Button",
                    tint = Color.White
                )
            }
        }
    }

    if (showBottomSheet) {
       ModalBottomSheet(
           onDismissRequest = {showBottomSheet = false},
           sheetState = sheetState
       ) {
           Text(text = "Schedule Workout", fontSize = 24.sp)
           Text(text = "This is where you can schedule workout stuff for selected day")
           Text(text = "Pretty poggers, amirite?")
       }
    }

}

fun lightenColor(color: Color, fraction: Float): Color {
    val hsv = FloatArray(3)
    ColorUtils.colorToHSL(color.toArgb(), hsv)
    hsv[2] += (1f - hsv[2]) * fraction
    return Color(ColorUtils.HSLToColor(hsv))
}

fun darkenColor(color: Color, fraction: Float): Color {
    val hsv = FloatArray(3)
    ColorUtils.colorToHSL(color.toArgb(), hsv)
    hsv[2] *= 1 - fraction
    return Color(ColorUtils.HSLToColor(hsv))
}

@Composable
fun PlanCard(
    modifier: Modifier = Modifier,
    color: Color = Color.Green,
    title: String = "Workout",
    duration: String = "1 Hour"
) {
    val bgColor = remember { lightenColor(color, 0.5f) }
    val txtColor = remember { darkenColor(color, 0.5f) }


    Box(
        modifier = modifier
            .padding(start = 50.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
            .fillMaxWidth()
            .background(bgColor, RoundedCornerShape(10.dp))
    ){
        Column(
            Modifier.padding(10.dp)
        ) {
            Text(title, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = txtColor)
            Text(duration, color = txtColor)
        }
    }
}