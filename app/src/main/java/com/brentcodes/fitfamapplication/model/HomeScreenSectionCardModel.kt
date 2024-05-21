package com.brentcodes.fitfamapplication.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.brentcodes.fitfamapplication.R

data class HomeScreenSectionCardModel(
    val duration: Int,
    val title: String,
    val subtitle: String,
    val image: Int = R.drawable.chestimage,
    val height: Int = 190,
    val width: Int = 240
)

data class HomeScreenSectionCardModelNew(
    val duration: Int,
    val exercises: Int,
    val title: String,
    val icon: ImageVector? = null,
    val iconTint: Color,
    val height: Int = 190,
    val width: Int = 240
)
