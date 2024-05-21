package com.brentcodes.fitfamapplication.ui.screens.home

import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.brentcodes.fitfamapplication.R
import com.brentcodes.fitfamapplication.model.HomeScreenSectionCardModel
import com.brentcodes.fitfamapplication.model.HomeScreenSectionCardModelNew
import com.brentcodes.fitfamapplication.model.Workout
import com.brentcodes.fitfamapplication.repo.AuthRepository
import com.brentcodes.fitfamapplication.ui.modifyColor
import com.brentcodes.fitfamapplication.ui.screens.DateBoxWeek
import com.brentcodes.fitfamapplication.ui.screens.Screen
import com.brentcodes.fitfamapplication.ui.theme.BackgroundGray
import com.brentcodes.fitfamapplication.ui.theme.DarkerGray
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.LocalDate
import java.time.YearMonth
import java.util.Calendar

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.userState.collectAsState()



    Column(
        Modifier
            .fillMaxSize()
            .background(BackgroundGray)
    ) {
        TopBar()
        Spacer(Modifier.height(20.dp))

        when (state.value) {
            LoggedinState.loading -> {
                Text(text = "I am loading!!")
            }

            is LoggedinState.loggedIn -> {
                mainBody()
            }
            LoggedinState.loggedout -> navController.navigate(Screen.SignUpScreen.route)
        }

        //BottomNavBar()
    }


}

@Composable
fun mainBody() {
    //WORKOUTS UNNECESSARY IN ORIGINAL
    val workouts = remember {
        listOf(
            HomeScreenSectionCardModelNew(20, 4, "Arms Workout", iconTint = Color.Red),
            HomeScreenSectionCardModelNew(16, 3, "Leg Routine", iconTint = Color.Blue),
            HomeScreenSectionCardModelNew(25, 5, "Core", iconTint = Color.White),
            HomeScreenSectionCardModelNew(12, 2, "Back Workout", iconTint = Color.Green),
            HomeScreenSectionCardModelNew(20, 3, "Cardio", iconTint = Color.Cyan)
        )
    }


    /*LazyColumn {
        item {
            HomeScreenSection(
                sectionTitle = "Plans",
                sectionSubtitle = "Design a custom plan",
                sectionCards = listOf(
                    HomeScreenSectionCardModel(
                        10,
                        "Bicep Routine",
                        "Strengthen",
                        R.drawable.bicepsimage
                    ),
                    HomeScreenSectionCardModel(
                        20,
                        "Leg Workout",
                        "Push with legs",
                        R.drawable.legsimage
                    ),
                    HomeScreenSectionCardModel(
                        10,
                        "Chest Press",
                        "Get bigger pecs",
                        R.drawable.chestimage
                    )
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
    }*/
    LazyColumn {
        item {
            HomeScreenSectionNew(
                sectionTitle = "Plans",
                sectionSubtitle = "Design a custom plan",
                sectionCards = workouts
            )
        }
        item {
            HomeScreenSectionNew(
                sectionTitle = "Performance",
                sectionCards = workouts
            )
        }
        item {
            HomeScreenSectionNew(
                sectionTitle = "Meals",
                sectionCards = workouts
            )
        }
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

    Column(
        modifier = Modifier
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
            fontSize = 20.sp
        )
        Text(text = "Well done! You have completed one workout this week!", color = Color.White)
        Spacer(modifier = Modifier.height(5.dp))
        WeekCalendar(
            state = stateOfCalendar,
            dayContent = { day ->
                DateBoxWeek(
                    day,
                    current = day.date == currentDate,
                    selected = day.date == selection?.date
                ) { clicked ->
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
fun HomeScreenSection(
    sectionTitle: String,
    sectionSubtitle: String? = null,
    sectionCards: List<HomeScreenSectionCardModel>
) {
    Column {
        Column(modifier = Modifier.padding(horizontal = 10.dp)) {
            Text(
                text = "$sectionTitle>>",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 20.sp
            )
            if (sectionSubtitle != null) {
                Text(text = "$sectionSubtitle>>", color = Color.LightGray)
            }
        }

        LazyRow {
            items(sectionCards) { card ->
                HomeScreenSectionCard(sectionCard = card)
            }
        }
    }
}

@Composable
fun HomeScreenSectionCard(
    sectionCard: HomeScreenSectionCardModel
) {
    val gradient = remember {
        Brush.verticalGradient(
            colors = listOf(Color.Transparent, DarkerGray),
            startY = 0F,
            endY = Float.POSITIVE_INFINITY
        )
    }

    Box(
        modifier = Modifier
            .size(sectionCard.width.dp, sectionCard.height.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .paint(
                painterResource(id = sectionCard.image),
                contentScale = ContentScale.FillBounds
            )
            .background(gradient)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(5.dp)
        ) {
            Text(text = "${sectionCard.duration} mins", color = Color.White, fontSize = 16.sp)
            Text(
                text = sectionCard.title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(text = sectionCard.subtitle, color = Color.LightGray)
        }
    }
}

@Composable
fun HomeScreenSectionNew(
    sectionTitle: String,
    sectionSubtitle: String? = null,
    sectionCards: List<HomeScreenSectionCardModelNew>
) {
    Column {
        Column(modifier = Modifier.padding(horizontal = 10.dp)) {
            Text(
                text = "$sectionTitle>>",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 20.sp
            )
            if (sectionSubtitle != null) {
                Text(text = "$sectionSubtitle>>", color = Color.LightGray)
            }
        }

        LazyRow {
            items(sectionCards) { card ->
                HomeScreenWorkoutCard(sectionCard = card, onClick = {})
            }
        }
    }
}

@Composable
fun HomeScreenSectionCardNew(
    sectionCard: HomeScreenSectionCardModelNew
) {
    Box(
        modifier = Modifier
            .size(sectionCard.width.dp, sectionCard.height.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(DarkerGray)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(5.dp)
        ) {
            Text(
                text = sectionCard.title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(text = "${sectionCard.duration} mins", color = Color.White, fontSize = 16.sp)
            Text(text = "${sectionCard.exercises} mins", color = Color.White, fontSize = 16.sp)
        }
    }
}

@Composable
fun HomeScreenWorkoutCard(
    modifier: Modifier = Modifier,
    sectionCard: HomeScreenSectionCardModelNew,
    onClick: () -> Unit
) {

    val durationId = "durationIcon"
    val durationText = buildAnnotatedString {
        appendInlineContent(durationId, "[icon]")
        append(" ${sectionCard.duration} Minutes")
    }
    val durationInlineContent = mapOf(Pair(durationId, InlineTextContent(
        Placeholder(
            width = 12.sp,
            height = 12.sp,
            placeholderVerticalAlign = PlaceholderVerticalAlign.Center
        )
    ) {
        Icon(imageVector = Icons.Filled.Timer, contentDescription = "Timer Icon", tint = Color.LightGray, modifier = Modifier.size(12.dp))
    }))

    val exercisesId = "exercisesIcon"
    val exercisesText = buildAnnotatedString {
        appendInlineContent(exercisesId, "[icon]")
        append(" ${sectionCard.exercises} Exercises")
    }

    val exercisesInlineContent = mapOf(Pair(exercisesId, InlineTextContent(
        Placeholder(
            width = 12.sp,
            height = 12.sp,
            placeholderVerticalAlign = PlaceholderVerticalAlign.Center
        )
    ) {
        Icon(imageVector = Icons.AutoMirrored.Filled.List, contentDescription = "List Icon", tint = Color.LightGray, modifier = Modifier.size(12.dp))
    }))

    val gradient = remember {
        Brush.verticalGradient(
            colors = listOf(DarkerGray, Color.DarkGray),
            startY = 0F,
            endY = Float.POSITIVE_INFINITY
        )
    }

    Box(
        modifier = modifier
            .size(sectionCard.width.dp, sectionCard.height.dp)
            .padding(15.dp)
            .background(gradient, RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
    ){
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.muscle_24),
            tint = modifyColor(sectionCard.iconTint, .6f, 0.7f),
            contentDescription = "Workout Icon",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp)
                .rotate(-30f)
                .size(70.dp)
        )
        androidx.compose.material3.Text(
            text = sectionCard.title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 20.dp, bottom = 40.dp),
            color = Color.White
        )
        androidx.compose.material3.Text(
            text = exercisesText,
            inlineContent = exercisesInlineContent,
            color = Color.LightGray,
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp)
        )
        androidx.compose.material3.Text(
            text = durationText,
            inlineContent = durationInlineContent,
            color = Color.LightGray,
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 20.dp, bottom = 5.dp)
        )
    }
}