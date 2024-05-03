package com.brentcodes.fitfamapplication.ui.screens.signup

import android.widget.Toast
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.brentcodes.fitfamapplication.ui.screens.CustomTextField
import com.brentcodes.fitfamapplication.ui.screens.Screen
import com.brentcodes.fitfamapplication.ui.screens.launch.LaunchCard
import com.brentcodes.fitfamapplication.ui.theme.RedAccent

@Preview
@Composable
fun SignUpScreen(navController: NavController = rememberNavController()) {
    TestSignUpScreen(navController = navController)
}

@Composable
fun TestSignUpScreen(
    textColor: Color = Color.White,
    fontSize: TextUnit = 12.sp,
    viewModel: SignUpViewModel = hiltViewModel(),
    navController: NavController = rememberNavController()
) {

    LaunchCard {

        val email by viewModel.email.collectAsState()
        val password by viewModel.password.collectAsState()
        val confirmpassword by viewModel.confirmpassword.collectAsState()
        val currentUser by viewModel.currentUser.collectAsState()
        val context = LocalContext.current

            when (currentUser) {
                loggedInState.loggedin -> navController.navigate(Screen.AuthenticatedScreen.route)
                loggedInState.loggedout -> {}
                loggedInState.error -> {
                    Toast.makeText(context, "Unable to Sign Up", Toast.LENGTH_SHORT).show()
                    viewModel.clearState()
                }
                loggedInState.invalidinput -> {
                    Toast.makeText(context, "Invalid Inputs", Toast.LENGTH_SHORT).show()
                    viewModel.clearState()
                }
            }



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
                value = confirmpassword,
                onValueChange = {
                    viewModel.setConfirmPassword(it)
                },
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
                onClick = {
                    viewModel.signUp()

                },
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = RedAccent,
                    contentColor = Color.White
                )
            ) {
                Text("Sign Up", color = Color.White, fontWeight = FontWeight.Bold)
            }
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                Text(text = "Have an account?", color = Color.White, fontSize = fontSize)
                Spacer(Modifier.width(5.dp))
                ClickableText(
                    text = AnnotatedString(
                        "Sign In",
                        spanStyle = SpanStyle(color = RedAccent, fontSize = fontSize)
                    ), onClick = {
                        navController.navigate(Screen.SignInScreen.route)
                    })
            }
        }
    }
}

/*
@Composable
fun CreateAccountComposable(
    textColor : Color = Color.White,
) {

    Column(
        modifier = Modifier
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        androidx.compose.material.Text(
            text = "Create an Account",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
        androidx.compose.material.Text(
            text = "Sign up for an account to keep track of your fitness journey wherever you go!",
            color = textColor,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))

        androidx.compose.material.Text(
            text = "Email",
            color = textColor,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 10.dp)
        )

        EmailTextfield(onValueChange = {}, modifier = Modifier)

        Spacer(modifier = Modifier.height(10.dp))
        androidx.compose.material.Text(
            text = "Password",
            color = textColor,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 10.dp)
        )
        PasswordTextfield(onValueChange = {}, modifier = Modifier)

        Spacer(modifier = Modifier.height(10.dp))
        androidx.compose.material.Text(
            text = "Confirm Password",
            color = textColor,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 10.dp)
        )
        PasswordTextfield(onValueChange = {}, modifier = Modifier)

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ElevatedButton(
                onClick = { },
                colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.White, contentColor = Color.DarkGray)
            ) {
                androidx.compose.material.Text("Sign Up", color = Color.DarkGray)
            }
            Spacer(modifier = Modifier.weight(1f))
            ElevatedButton(
                onClick = {},
                colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.Transparent, contentColor = Color.White),
                border = BorderStroke(2.dp, Color.White)
            ) {
                androidx.compose.material.Text(text = "Log In", color = Color.White)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextfield(modifier: Modifier = Modifier, onValueChange: (String) -> Unit = {}) {

    var text by remember {
        mutableStateOf("")
    }

    TextField(
        value = text,
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(autoCorrect = false),
        onValueChange = {changedString ->
            if (!changedString.contains("\n")) {
                text = changedString
                onValueChange(changedString)
            }
        },
        label = { Text("Email") },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextfield(modifier: Modifier = Modifier, onValueChange: (String) -> Unit = {}) {

    var visible by remember {
        mutableStateOf(false)
    }

    var text by remember {
        mutableStateOf("")
    }

    TextField(
        value = text,
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(autoCorrect = false),
        visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (visible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            val description = if (visible) "Hide password" else "Show password"

            IconButton(onClick = {visible = !visible}) {
                Icon(imageVector = image, contentDescription = description)
            }
        },
        onValueChange = {changedString ->
            if (!changedString.contains("\n")) {
                text = changedString
                onValueChange(changedString)
            }
        },
        label = { Text("Password") },
        modifier = modifier
    )
}*/
