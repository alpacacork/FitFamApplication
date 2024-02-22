package com.brentcodes.fitfamapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brentcodes.fitfamapplication.ui.screens.launch.LaunchCard
import com.brentcodes.fitfamapplication.ui.theme.RedAccent

@Preview
@Composable
fun TestScreen() {
    TestSignInScreen()
}

@Composable
fun TestSignInScreen(
    textColor : Color = Color.White,
    fontSize: TextUnit = 12.sp
) {
    LaunchCard {
        Column(
            modifier = Modifier
                .padding(horizontal = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sign In",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
            Text(
                text = "Use your details to access your account and saved workouts!",
                fontSize = fontSize,
                lineHeight = 16.sp,
                color = textColor,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Email",
                fontSize = fontSize,
                lineHeight = 16.sp,
                color = textColor,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 5.dp)
            )
            CustomTextField(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.surface,
                        RoundedCornerShape(percent = 50)
                    )
                    .padding(5.dp)
                    .height(24.dp),
                fontSize = 14.sp,
                placeholderText = "Email",
                isPassword = false
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = "Password",
                fontSize = fontSize,
                lineHeight = 16.sp,
                color = textColor,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 5.dp)
            )
            CustomTextField(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.surface,
                        RoundedCornerShape(percent = 50)
                    )
                    .padding(5.dp)
                    .height(24.dp),
                fontSize = 14.sp,
                placeholderText = "Password",
                isPassword = true
            )
            Spacer(modifier = Modifier.height(30.dp))
            ElevatedButton(
                onClick = { },
                colors = ButtonDefaults.elevatedButtonColors(containerColor = RedAccent, contentColor = Color.White)
            ) {
                Text("Sign In", color = Color.White, fontWeight = FontWeight.Bold)
            }
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                Text(text = "Don't have an account?", color = Color.White, fontSize = fontSize)
                Spacer(Modifier.width(5.dp))
                ClickableText(text = AnnotatedString("Sign Up", spanStyle = SpanStyle(color = RedAccent, fontSize = fontSize)), onClick = {

                })
            }
        }
    }
}

@Composable
fun TestSignUpScreen(
    textColor : Color = Color.White,
    fontSize: TextUnit = 12.sp
) {
    LaunchCard {
        Column(
            modifier = Modifier
                .padding(horizontal = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sign Up",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
            Text(
                text = "Register to keep track of your workouts wherever you go!",
                fontSize = fontSize,
                lineHeight = 16.sp,
                color = textColor,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Email",
                fontSize = fontSize,
                lineHeight = 16.sp,
                color = textColor,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 5.dp)
            )
            CustomTextField(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.surface,
                        RoundedCornerShape(percent = 50)
                    )
                    .padding(5.dp)
                    .height(24.dp),
                fontSize = 14.sp,
                placeholderText = "Email",
                isPassword = false
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = "Password",
                fontSize = fontSize,
                lineHeight = 16.sp,
                color = textColor,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 5.dp)
            )
            CustomTextField(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.surface,
                        RoundedCornerShape(percent = 50)
                    )
                    .padding(5.dp)
                    .height(24.dp),
                fontSize = 14.sp,
                placeholderText = "Password",
                isPassword = true
            )
            Spacer(Modifier.height(10.dp))

            Text(
                text = "Confirm Password",
                fontSize = fontSize,
                lineHeight = 16.sp,
                color = textColor,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 5.dp)
            )
            CustomTextField(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.surface,
                        RoundedCornerShape(percent = 50)
                    )
                    .padding(5.dp)
                    .height(24.dp),
                fontSize = 14.sp,
                placeholderText = "Confirm Password",
                isPassword = true
            )
            Spacer(modifier = Modifier.height(30.dp))
            ElevatedButton(
                onClick = { },
                colors = ButtonDefaults.elevatedButtonColors(containerColor = RedAccent, contentColor = Color.White)
            ) {
                Text("Sign Up", color = Color.White, fontWeight = FontWeight.Bold)
            }
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                Text(text = "Have an account?", color = Color.White, fontSize = fontSize)
                Spacer(Modifier.width(5.dp))
                ClickableText(text = AnnotatedString("Sign In", spanStyle = SpanStyle(color = RedAccent, fontSize = fontSize)), onClick = {

                })
            }
        }
    }
}