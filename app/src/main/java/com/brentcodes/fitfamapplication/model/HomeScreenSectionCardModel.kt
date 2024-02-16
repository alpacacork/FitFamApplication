package com.brentcodes.fitfamapplication.model

import android.media.Image
import androidx.compose.ui.unit.Dp
import com.brentcodes.fitfamapplication.R

data class HomeScreenSectionCardModel(
    val duration: Int,
    val title: String,
    val subtitle: String,
    val image: Int = R.drawable.chestimage,
    val height: Int = 190,
    val width: Int = 240
)
