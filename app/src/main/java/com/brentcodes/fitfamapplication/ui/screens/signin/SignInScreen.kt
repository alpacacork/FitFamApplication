package com.brentcodes.fitfamapplication.ui.screens.signin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.brentcodes.fitfamapplication.ui.screens.CustomTextField
import com.brentcodes.fitfamapplication.ui.screens.launch.LaunchCard
import com.brentcodes.fitfamapplication.ui.theme.RedAccent

@Preview
@Composable
fun SignInScreen() {
    TestSignInScreen()
}

@Composable
fun TestSignInScreen(
    textColor : Color = Color.White,
    fontSize: TextUnit = 12.sp,
    viewModel: SignInViewModel = hiltViewModel()
) {
    LaunchCard {

        val email by viewModel.email.collectAsState()
        val password by viewModel.password.collectAsState()

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
                value = email,
                onValueChange = {
                    viewModel.setEmail(it)
                },
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
                value = password,
                onValueChange = {
                    viewModel.setPassword(it)
                },
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

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInComposable(
    textColor : Color = Color.White,
) {

    Column(
        modifier = Modifier
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        androidx.compose.material.Text(
            text = "Sign In",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
        androidx.compose.material.Text(
            text = "Use your details to access your account and saved workouts!",
            color = textColor,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(30.dp))

        androidx.compose.material.Text(
            text = "Email",
            color = textColor,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 10.dp)
        )

        EmailTextfield(onValueChange = {}, modifier = Modifier*/
/*.height(60.dp).padding(vertical = 10.dp)*//*
)

        Spacer(modifier = Modifier.height(10.dp))
        androidx.compose.material.Text(
            text = "Password",
            color = textColor,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 10.dp)
        )
        PasswordTextfield(onValueChange = {}, modifier = Modifier*/
/*.height(60.dp).padding(vertical = 10.dp)*//*
)

        Spacer(modifier = Modifier.height(20.dp))

        ElevatedButton(
            onClick = { },
            colors = ButtonDefaults.elevatedButtonColors(containerColor = RedAccent, contentColor = Color.White)
        ) {
            androidx.compose.material.Text("Sign Up", color = Color.White, fontWeight = FontWeight.Bold)
        }
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            Text(text = "Don't have an account?", color = Color.White)
            Spacer(Modifier.width(5.dp))
            ClickableText(text = AnnotatedString("Sign Up", spanStyle = SpanStyle(color = RedAccent)), onClick = {

            })
        }

    }
}*/
