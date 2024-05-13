package com.brentcodes.fitfamapplication.ui.screens.launch

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.brentcodes.fitfamapplication.R
import com.brentcodes.fitfamapplication.ui.theme.BackgroundGray
import com.brentcodes.fitfamapplication.ui.theme.DarkerGray

@Composable
fun LaunchCard(
    content: @Composable () -> Unit
) {

    //OVERALL SCREEN BOX, MAINTAINS BACKGROUND
    Box(modifier = Modifier
        .fillMaxSize()
        .background(BackgroundGray))
    {

        //BOX TO HOLD THE CARD, CONTENT DECIDED BY COMPOSABLES IN BOXSCOPE
        Box(modifier = Modifier
            .align(Alignment.Center)
            .clip(RoundedCornerShape(20.dp))
            .background(DarkerGray)
            .size(300.dp, 600.dp)
        ) {

            val textColor = remember {
                Color.White
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo of the Application",
                    colorFilter = ColorFilter.tint(textColor),
                    modifier = Modifier.width(width = 180.dp),
                    contentScale = ContentScale.FillWidth
                )
                Spacer(modifier = Modifier.height(20.dp))
                content()
            }
        }
    }
}