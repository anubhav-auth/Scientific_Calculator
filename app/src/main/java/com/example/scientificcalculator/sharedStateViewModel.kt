package com.example.scientificcalculator

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

class sharedStateViewModel: ViewModel() {
    var swapState by mutableStateOf(true)
    var angleUnitState by mutableStateOf(true)
    var sizeState by mutableStateOf(460.dp)
    var ButtonScale by mutableStateOf(0f)
    var inverseState by mutableStateOf(true)
    var element by mutableStateOf("")
    var answer by mutableStateOf("")

}