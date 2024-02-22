package com.brentcodes.fitfamapplication.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brentcodes.fitfamapplication.R
import com.brentcodes.fitfamapplication.model.HomeScreenSectionCardModel
import com.brentcodes.fitfamapplication.ui.theme.BackgroundGray
import com.brentcodes.fitfamapplication.ui.theme.DarkerGray
import com.brentcodes.fitfamapplication.ui.theme.RedAccent
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.LocalDate
import java.time.YearMonth
import java.util.Calendar

@Preview
@Composable
fun HomeScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .background(BackgroundGray)
    ) {
        TopBar()
        Spacer(Modifier.height(20.dp))
        LazyColumn {
            item {
                HomeScreenSection(
                    sectionTitle = "Plans",
                    sectionSubtitle = "Design a custom plan",
                    sectionCards = listOf(
                        HomeScreenSectionCardModel(10, "Bicep Routine", "Strengthen", R.drawable.bicepsimage),
                        HomeScreenSectionCardModel(20, "Leg Workout", "Push with legs", R.drawable.legsimage),
                        HomeScreenSectionCardModel(10, "Chest Press", "Get bigger pecs", R.drawable.chestimage)
                    )
                )
            }
            item {
                HomeScreenSection(
                    sectionTitle = "Performance",
                    sectionCards = listOf(
                        HomeScreenSectionCardModel(10, "Bicep Routine", "Strengthen"),
                        HomeScreenSectionCardModel(20, "Leg Workout", "Push with legs"),
                        HomeScreenSectionCardModel(10, "Chest Press", "Get bigger pecs")
                    )
                )
            }
            item {
                HomeScreenSection(
                    sectionTitle = "Meals",
                    sectionCards = listOf(
                        HomeScreenSectionCardModel(10, "Bicep Routine", "Strengthen"),
                        HomeScreenSectionCardModel(20, "Leg Workout", "Push with legs"),
                        HomeScreenSectionCardModel(10, "Chest Press", "Get bigger pecs")
                    )
                )
            }
        }
        //BottomNavBar()
    }
}

@Composable
fun TopBar() {

    var expanded by remember {
        mutableStateOf(false)
    }
    var selection by remember {
        mutableStateOf<WeekDay?>(null)
    }

    val height = if (selection != null) 160.dp else 120.dp

    val currentTime = remember { Calendar.getInstance().get(Calendar.HOUR_OF_DAY) }
    val currentDate = remember { LocalDate.now() }
    val currentMonth = remember { YearMonth.now() }
    val startDate = remember { currentMonth.minusMonths(12).atStartOfMonth() }
    val endDate = remember { currentMonth.plusMonths(12).atEndOfMonth() }
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }

    val stateOfCalendar = rememberWeekCalendarState(
        startDate = startDate,
        endDate = endDate,
        firstVisibleWeekDate = currentDate,
        firstDayOfWeek = firstDayOfWeek
    )

    Column(modifier = Modifier
        .fillMaxWidth()
        .height(height)
        .shadow(
            elevation = 10.dp,
            shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
        )
        .background(DarkerGray)
        .padding(10.dp)
    ) {
        val greeting = if (currentTime < 12) {
            "Good Morning, Brent."
        } else if (currentTime < 18) {
            "Good Afternoon, Brent."
        } else {
            "Good Evening, Brent."
        }

        Text(
            text = greeting,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp)
        Text(text = "Well done! You have completed one workout this week!", color = Color.White)
        Spacer(modifier = Modifier.height(5.dp))
        WeekCalendar (
            state = stateOfCalendar,
            dayContent = {day ->
                DateBox(day, current = day.date==currentDate, selected = day.date == selection?.date) { clicked ->
                    selection = if (selection == clicked) null else clicked
                }
            }
        )
        if (selection != null) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "You have selected ${selection!!.date.dayOfWeek}", color = Color.White)
        }
    }
}

@Composable
fun Day(day: WeekDay) {
    Box(
        modifier = Modifier
            .aspectRatio(1f), // This is important for square sizing!
        contentAlignment = Alignment.Center
    ) {
        Text(text = day.date.dayOfMonth.toString())
    }
}

@Composable
fun DateBox(
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
            .clickable (
                onClick = {onClick(day)}
            )
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            modifier = Modifier.align(Alignment.Center),
            color = Color.White,
            fontSize = 16.sp
        )
    }
}



@Composable
fun HomeScreenSection(
    sectionTitle: String,
    sectionSubtitle: String? = null,
    sectionCards: List<HomeScreenSectionCardModel>
) {
    Column {
        Column(modifier = Modifier.padding(horizontal = 10.dp)) {
            Text(text = "$sectionTitle>>", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 20.sp)
            if (sectionSubtitle != null) {
                Text(text = "$sectionSubtitle>>", color = Color.LightGray)
            }
        }

        LazyRow {
            items(sectionCards) {card ->
                HomeScreenSectionCard(sectionCard = card)
            }
        }

    }
}

@Composable
fun HomeScreenSectionCard(
    sectionCard : HomeScreenSectionCardModel
) {
    val gradient = remember {
        Brush.verticalGradient(
            colors = listOf(Color.Transparent, DarkerGray),
            startY = 0F,
            endY = Float.POSITIVE_INFINITY
        )
    }

    Box(modifier = Modifier
        .size(sectionCard.width.dp, sectionCard.height.dp)
        .padding(10.dp)
        .clip(RoundedCornerShape(10.dp))
        .paint(
            painterResource(id = sectionCard.image),
            contentScale = ContentScale.FillBounds
        )
        .background(gradient)
    ) {
        Column(modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(5.dp)) {
            Text(text = "${sectionCard.duration} mins", color = Color.White, fontSize = 16.sp)
            Text(text = sectionCard.title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = sectionCard.subtitle, color = Color.LightGray)
        }
    }
}