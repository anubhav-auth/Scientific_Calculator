package com.example.scientificcalculator

import android.graphics.drawable.Icon
import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItems(
    val id : String,
    val name : String,
    val icon: ImageVector,
    val contentDescription : String
)
