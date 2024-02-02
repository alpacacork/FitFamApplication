package com.brentcodes.fitfamapplication.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Button
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brentcodes.fitfamapplication.R
import com.google.firebase.Firebase
import com.google.firebase.app

@SuppressLint("RememberReturnType")
@Preview
@Composable
fun LaunchScreen() {

    //OVERALL SCREEN BOX, MAINTAINS BACKGROUND
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray))
        {

        //BOX TO HOLD THE CARD, CONTENT DECIDED BY COMPOSABLES IN BOXSCOPE
        Box(modifier = Modifier
            .align(Alignment.Center)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.DarkGray)
            .size(300.dp, 500.dp)
        ) {

            val textColor = remember {
                Color.White
            }

            Column(horizontalAlignment = CenterHorizontally) {
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo of the Application",
                    colorFilter = ColorFilter.tint(textColor),
                    modifier = Modifier.width(width = 180.dp),
                    contentScale = ContentScale.FillWidth
                )
                Spacer(modifier = Modifier.height(50.dp))
                LaunchCardTwo(textColor = textColor)
            }
        }
    }
}

@Composable
fun ContinueButton(onClick: () -> Unit = { Log.d("click", "Continue was clicked")},
                   modifier: Modifier = Modifier) {
    ElevatedButton(
        modifier = modifier,
        border = BorderStroke(2.dp, Color.White),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.DarkGray, contentColor = Color.White),
        onClick = onClick,
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 10.dp)
    ) {
        Text(text = "Continue", fontSize = 12.sp, color = Color.White)
    }
}

@Composable
fun LaunchCardOne(
    textColor : Color = Color.White,
) {

    Column(
        modifier = Modifier
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Track",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
        Text(
            text = "See improvements, visualize progress and beat personal bests",
            color = textColor,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Plan",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
        Text(
            text = "Build your own custom routines and organize a personal schedule",
            color = textColor,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Holistic",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
        Text(
            text = "Meet all aspects of health including fitness, nutrition and weight goals",
            color = textColor,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        ContinueButton(modifier = Modifier)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchCardTwo(
    textColor : Color = Color.White,
) {

    Column(
        modifier = Modifier
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Create an Account",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
        Text(
            text = "Sign up for an account to keep track of your fitness journey wherever you go!",
            color = textColor,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Email",
            color = textColor,
            textAlign = TextAlign.Start
        )
        TextField(value = "", onValueChange = {}, modifier = Modifier.height(20.dp))
        
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Password",
            color = textColor,
            textAlign = TextAlign.Center
        )
        TextField(value = "", onValueChange = {}, modifier = Modifier.height(20.dp))
        
        Spacer(modifier = Modifier.height(20.dp))
        
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ElevatedButton(
                onClick = { },
                colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.White, contentColor = Color.DarkGray)
            ) {
                Text("Sign Up", color = Color.DarkGray)
            }
            ElevatedButton(
                onClick = {},
                colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.DarkGray, contentColor = Color.White),
                border = BorderStroke(2.dp, Color.White)
            ) {
                Text(text = "Log In", color = Color.White)
            }
        }
    }
}

