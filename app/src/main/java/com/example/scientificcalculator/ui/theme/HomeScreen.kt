package com.example.scientificcalculator.ui.theme

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.core.EaseInOutBack
import androidx.compose.animation.core.EaseOutBounce
import androidx.compose.animation.core.EaseOutQuint
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.estimateAnimationDurationMillis
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scientificcalculator.MainActivity
import com.example.scientificcalculator.sharedStateViewModel
import org.mariuszgromada.math.mxparser.*
@Composable
fun HomeScreen() {
    val viewModel: sharedStateViewModel = viewModel()
    val state by animateDpAsState(
        targetValue = viewModel.sizeState,
        spring(
            Spring.DampingRatioHighBouncy
        )
    )
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val height = constraints.maxHeight
        val width = constraints.maxWidth
        val gradient = Brush.linearGradient(
            colors = listOf(GradColor1, GradColor2, GradColor1),
            start = Offset(width * 0f, height * 0f),
            end = Offset(width * 0.8f, height * 0.8f)
        )
        Box(
            modifier = Modifier
                .background(gradient)
                .fillMaxSize()
        ) {
            Column {
                display()
                sciKeyPad(ButtonSize = if (viewModel.swapState) 75.dp else 62.dp, rowSize = state)
            }

        }
    }

}

@Composable
fun display() {
    val viewModel: sharedStateViewModel = viewModel()
    var interactionSource = remember { MutableInteractionSource() }

    Card(
        modifier = Modifier
            .size(400.dp)
            .padding(top = 50.dp, start = 15.dp, end = 15.dp, bottom = 15.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(Color.Transparent),
//        elevation = CardDefaults.cardElevation(500.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                val height = constraints.maxHeight
                val width = constraints.maxWidth
                val gradient = Brush.linearGradient(
                    colors = listOf(Color.Transparent, GradColor2),
                    start = Offset(width * 0.3f, height * 0.7f),
                    end = Offset(width * 1f, height * 0f)
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(gradient)
                        .shadow(400.dp, RoundedCornerShape(20.dp), ambientColor = Color.Black,)
                        .blur(30.dp)
                )
            }

            Box(modifier = Modifier.fillMaxSize()) {
                Icon(
                    Icons.Rounded.Menu,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(top = 13.dp, start = 22.dp)
                        .clickable { },
                    tint = Color.Black,

                    )
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.End

                ) {
                    Row(
                        modifier = Modifier
                            .height(50.dp)
                            .width(180.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Spacer(modifier = Modifier.weight(1f))

                        Text(
                            modifier = Modifier
                                .padding(top = 9.dp, end = 30.dp)
                                .clickable {
                                    viewModel.swapState = !viewModel.swapState
                                    viewModel.angleUnitState = true
                                    if (viewModel.sizeState == 460.dp) viewModel.sizeState = 500.dp
                                    else viewModel.sizeState = 460.dp
                                    if (viewModel.swapState) viewModel.ButtonScale = 0f
                                    else viewModel.ButtonScale = 1f
                                    viewModel.inverseState = true
                                },
                            textAlign = TextAlign.Center,
                            text = if (viewModel.swapState) "Sci"
                            else "Norm",
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            modifier = Modifier
                                .padding(top = 9.dp, end = 30.dp)
                                .clickable {
                                    if (viewModel.inverseState) {
                                        viewModel.angleUnitState = !viewModel.angleUnitState
                                    }
                                },
                            textAlign = TextAlign.End,
                            text = if(viewModel.swapState)"Deg"
                                    else {
                                        if (viewModel.angleUnitState || !viewModel.inverseState) "Deg"
                                        else "Rad"
                                         },
                            color = if(viewModel.swapState || !viewModel.inverseState) Color.Gray
                                    else Color.Black,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge

                        )

                    }
                    Row(
                        modifier = Modifier
                            .height(53.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(top = 5.dp, end = 30.dp)
                                .clickable {
                                    viewModel.element = viewModel.equalAnsStore
                                    viewModel.answer = ""
                                    viewModel.equalClicked = false
                                },
                            textAlign = TextAlign.End,
                            text = "Ans",
                            color = Color(0xFFF9F871),//ClearOperator,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.headlineLarge
                        )
                        Text(
                            modifier = Modifier
                                .padding(top = 5.dp, end = 30.dp)
                                .clickable {
                                    viewModel.element = ""
                                    viewModel.answer = ""
                                    viewModel.equalClicked = false
                                },
                            textAlign = TextAlign.End,
                            text = "AC",
                            color = Color(0xFFF9F871),//ClearOperator,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.headlineLarge
                        )
                        Text(
                            modifier = Modifier
                                .padding(top = 5.dp, end = 30.dp)
                                .clickable {

                                    viewModel.element = viewModel.element.dropLast(1)
                                    viewModel.equalClicked = false
                                },
                            textAlign = TextAlign.End,
                            text = "C",
                            color = Color(0xFFF9F871),//ClearOperator,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.headlineLarge

                        )

                    }
                }
            }

            Box(modifier = Modifier
                .padding(vertical = 54.dp)
                .fillMaxSize()
            ){
                Column (modifier = Modifier.fillMaxSize()){

                    Row (modifier = Modifier
                        .weight(0.6f)
                        .fillMaxSize()
                        .padding(end = 21.dp, start = 21.dp),
                        horizontalArrangement = Arrangement.End
                    ){
                        Column (horizontalAlignment = Alignment.End){
                            Text(
                                modifier = Modifier
                                    .weight(0.64f)
                                    .fillMaxSize(),
                                text = viewModel.element,
                                fontWeight = if (!viewModel.equalClicked)FontWeight.SemiBold
                                else FontWeight.Normal,
                                textAlign = TextAlign.Right,
                                fontSize = if (!viewModel.equalClicked)30.sp
                                            else 25.sp,
                                color = if (!viewModel.equalClicked) ButtonTextColor
                                            else Color.DarkGray
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            Text(
                                modifier = Modifier
                                    .weight(0.36f)
                                    .fillMaxSize(),
                                text = viewModel.answer,
                                fontWeight = if (viewModel.equalClicked)FontWeight.SemiBold
                                            else FontWeight.Normal,
                                textAlign = TextAlign.Right,
                                fontSize = if (viewModel.equalClicked)35.sp
                                            else 25.sp,
                                color = if (viewModel.equalClicked) ButtonTextColor
                                else Color.DarkGray
                            )
                        }
                        }
                }
            }

        }

    }
}


@Composable
fun sciKeyPad(ButtonSize: Dp, rowSize: Dp) {
    val viewModel: sharedStateViewModel = viewModel()
    val answer = Expression(viewModel.element)

    if (!viewModel.angleUnitState) mXparser.setRadiansMode()
    else mXparser.setDegreesMode()
    mXparser.checkIfDegreesMode()


    if (answer.checkSyntax()){
        val ans = answer.calculate().toString()
        viewModel.answer = ans
    }


    val scaleButton = animateDpAsState(
        targetValue = ButtonSize * viewModel.ButtonScale
    )
    val maxNoOfElements = 32

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Row(
            modifier = Modifier
                .size(rowSize)
                .padding(top = 6.dp, start = 12.dp, end = 12.dp, bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!viewModel.swapState) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(bottom = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {


                    Button(

                        onClick = {
                                if (viewModel.angleUnitState)
                                      viewModel.inverseState = !viewModel.inverseState
                        },
                        contentPadding = PaddingValues(horizontal = 0.dp),
                        modifier = Modifier
                            .size(scaleButton.value)
                        ,
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(ButtonColor),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 8.dp,
                            pressedElevation = 0.dp
                        )
                    ) {
                            Text(
                                modifier = Modifier,
                                text = "inv",
                                color = if (!viewModel.inverseState) Color.Gray
                                        else if(!viewModel.swapState && !viewModel.angleUnitState) Color.Gray
                                        else ButtonTextColor
                                ,
                                fontSize = 21.sp,
                                fontWeight = FontWeight.Medium
                            )
                    }

                    Button(
                        onClick = { if (viewModel.element.length < maxNoOfElements)viewModel.element += "^"
                            if (viewModel.equalClicked) viewModel.equalClicked = false},
                        contentPadding = PaddingValues(horizontal = 0.dp),
                        modifier = Modifier
                            .size(scaleButton.value),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(ButtonColor),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 8.dp,
                            pressedElevation = 0.dp
                        )
                    ) {

                        Text(
                            text = "^",
                            color = ButtonTextColor,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Button(
                        onClick = { if (viewModel.element.length < maxNoOfElements)viewModel.element += "√"
                            if (viewModel.equalClicked) viewModel.equalClicked = false},
                        contentPadding = PaddingValues(horizontal = 0.dp),
                        modifier = Modifier
                            .size(scaleButton.value),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(ButtonColor),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 8.dp,
                            pressedElevation = 0.dp
                        )
                    ) {

                        Text(
                            text = "\u221Ax",
                            color = ButtonTextColor,
                            fontSize = 21.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Button(
                        onClick = { if (viewModel.element.length < maxNoOfElements)viewModel.element += "!"
                            if (viewModel.equalClicked) viewModel.equalClicked = false},
                        contentPadding = PaddingValues(horizontal = 0.dp),
                        modifier = Modifier
                            .size(scaleButton.value),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(ButtonColor),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 8.dp,
                            pressedElevation = 0.dp
                        )
                    ) {

                        Text(
                            text = "x!",
                            color = ButtonTextColor,
                            fontSize = 21.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }


                    Button(
                        onClick = { if (viewModel.element.length < maxNoOfElements)viewModel.element += "π"
                            if (viewModel.equalClicked) viewModel.equalClicked = false},
                        contentPadding = PaddingValues(horizontal = 0.dp),
                        modifier = Modifier
                            .size(scaleButton.value),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(ButtonColor),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 8.dp,
                            pressedElevation = 0.dp
                        )
                    ) {

                        Text(
                            text = "\u03C0",
                            color = ButtonTextColor,
                            fontSize = 21.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }


                    Button(
                        onClick = { if (viewModel.element.length < maxNoOfElements)viewModel.element += "ln("
                            if (viewModel.equalClicked) viewModel.equalClicked = false},
                        contentPadding = PaddingValues(horizontal = 0.dp),
                        modifier = Modifier
                            .size(scaleButton.value),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(ButtonColor),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 8.dp,
                            pressedElevation = 0.dp
                        )
                    ) {

                        Text(

                            text = "ln",
                            color = ButtonTextColor,
                            fontSize = 21.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(bottom = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                if (!viewModel.swapState) {
                    Button(
                        onClick = {
                            if (viewModel.element.length < maxNoOfElements)viewModel.element += "lg("
                            if (viewModel.equalClicked) viewModel.equalClicked = false},
                        contentPadding = PaddingValues(horizontal = 0.dp),
                        modifier = Modifier
                            .size(scaleButton.value),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(ButtonColor),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 8.dp,
                            pressedElevation = 0.dp
                        )
                    ) {

                        Text(
                            text = "log",
                            color = ButtonTextColor,
                            fontSize = 21.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Button(
                    onClick = { if (viewModel.element.length < maxNoOfElements)viewModel.element += "("
                        if (viewModel.equalClicked) viewModel.equalClicked = false},
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    modifier = Modifier
                        .size(ButtonSize),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(ButtonColor),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 0.dp
                    )
                ) {

                    Text(
                        text = "(",
                        color = ButtonTextColor,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                }


                Button(
                    onClick = { if (viewModel.element.length < maxNoOfElements)viewModel.element += "7"
                        if (viewModel.equalClicked) viewModel.equalClicked = false},
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    modifier = Modifier
                        .size(ButtonSize),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(NumButtonColor),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 0.dp
                    )
                ) {

                    Text(
                        text = "7",
                        color = ButtonTextColor,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Button(
                    onClick = { if (viewModel.element.length < maxNoOfElements)viewModel.element += "4"
                        if (viewModel.equalClicked) viewModel.equalClicked = false},
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    modifier = Modifier
                        .size(ButtonSize),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(NumButtonColor),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 0.dp
                    )
                ) {

                    Text(
                        text = "4",
                        color = ButtonTextColor,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                }


                Button(
                    onClick = { if (viewModel.element.length < maxNoOfElements)viewModel.element += "1"
                        if (viewModel.equalClicked) viewModel.equalClicked = false},
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    modifier = Modifier
                        .size(ButtonSize),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(NumButtonColor),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 0.dp
                    )
                ) {

                    Text(
                        text = "1",
                        color = ButtonTextColor,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                }


                Button(
                    onClick = {
                        if (viewModel.element.length < maxNoOfElements){
                            viewModel.element += if (viewModel.swapState) "00"
                                                    else "e"
                            }

                        if (viewModel.equalClicked) viewModel.equalClicked = false
                              },
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    modifier = Modifier
                        .size(ButtonSize),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        if (!viewModel.swapState)ButtonColor
                        else NumButtonColor
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 0.dp
                    )
                ) {

                    Text(

                        text = if (viewModel.swapState) "00"
                        else "e",
                        color = ButtonTextColor,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(bottom = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                if (!viewModel.swapState) {

                    Button(
                        onClick = {
                            if (viewModel.element.length < maxNoOfElements){
                                if (viewModel.inverseState){
                                    viewModel.element += "sin("
                                }else {
                                    viewModel.element += "asin("
                                }
                            }

                            if (viewModel.equalClicked) viewModel.equalClicked = false
                        },
                        contentPadding = PaddingValues(horizontal = 0.dp),
                        modifier = Modifier
                            .size(scaleButton.value),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(ButtonColor),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 8.dp,
                            pressedElevation = 0.dp
                        )
                    ) {

                        Row {
                            Text(
                                text = "sin",
                                color = ButtonTextColor,
                                fontSize = 21.sp,
                                fontWeight = FontWeight.Medium
                            )
                            if (!viewModel.inverseState) {
                                Text(
                                    text = "-1",
                                    color = ButtonTextColor,
                                    fontSize = 10.sp,
                                )
                            }
                        }
                    }
                }


                Button(
                    onClick = { if (viewModel.element.length < maxNoOfElements)viewModel.element += ")"
                        if (viewModel.equalClicked) viewModel.equalClicked = false},
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    modifier = Modifier
                        .size(ButtonSize),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(ButtonColor),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 0.dp
                    )
                ) {

                    Text(
                        text = ")",
                        color = ButtonTextColor,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                }


                Button(
                    onClick = { if (viewModel.element.length < maxNoOfElements)viewModel.element += "8"
                        if (viewModel.equalClicked) viewModel.equalClicked = false},
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    modifier = Modifier
                        .size(ButtonSize),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(NumButtonColor),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 0.dp
                    )
                ) {

                    Text(
                        text = "8",
                        color = ButtonTextColor,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Button(
                    onClick = { if (viewModel.element.length < maxNoOfElements)viewModel.element += "5"
                        if (viewModel.equalClicked) viewModel.equalClicked = false},
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    modifier = Modifier
                        .size(ButtonSize),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(NumButtonColor),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 0.dp
                    )
                ) {

                    Text(
                        text = "5",
                        color = ButtonTextColor,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                }


                Button(
                    onClick = { if (viewModel.element.length < maxNoOfElements)viewModel.element += "2"
                        if (viewModel.equalClicked) viewModel.equalClicked = false},
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    modifier = Modifier
                        .size(ButtonSize),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(NumButtonColor),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 0.dp
                    )
                ) {

                    Text(
                        text = "2",
                        color = ButtonTextColor,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                }


                Button(
                    onClick = { if (viewModel.element.length < maxNoOfElements)viewModel.element += "0"
                        if (viewModel.equalClicked) viewModel.equalClicked = false},
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    modifier = Modifier
                        .size(ButtonSize),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(NumButtonColor),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 0.dp
                    )
                ) {

                    Text(
                        text = "0",
                        color = ButtonTextColor,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                }


            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(bottom = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                if (!viewModel.swapState) {
                    Button(
                        onClick = {
                            if (viewModel.element.length < maxNoOfElements){
                                if (viewModel.inverseState){
                                    viewModel.element += "cos("
                                }else {
                                    viewModel.element += "acos("
                                }
                            }

                            if (viewModel.equalClicked) viewModel.equalClicked = false
                                  },
                        contentPadding = PaddingValues(horizontal = 0.dp),
                        modifier = Modifier
                            .size(scaleButton.value),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(ButtonColor),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 8.dp,
                            pressedElevation = 0.dp
                        )
                    ) {

                        Row {
                            Text(
                                text = "cos",
                                color = ButtonTextColor,
                                fontSize = 21.sp,
                                fontWeight = FontWeight.Medium
                            )
                            if (!viewModel.inverseState) {
                                Text(
                                    text = "-1",
                                    color = ButtonTextColor,
                                    fontSize = 10.sp,
                                )
                            }
                        }
                    }
                }


                Button(
                    onClick = { if (viewModel.element.length < maxNoOfElements)viewModel.element += "%"
                        if (viewModel.equalClicked) viewModel.equalClicked = false},
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    modifier = Modifier
                        .size(ButtonSize),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(ButtonColor),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 0.dp
                    )
                ) {

                    Text(
                        text = "%",
                        color = ButtonTextColor,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                }


                Button(
                    onClick = { if (viewModel.element.length < maxNoOfElements)viewModel.element += "9"
                        if (viewModel.equalClicked) viewModel.equalClicked = false},
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    modifier = Modifier
                        .size(ButtonSize),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(NumButtonColor),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 0.dp
                    )
                ) {

                    Text(
                        text = "9",
                        color = ButtonTextColor,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Button(
                    onClick = { if (viewModel.element.length < maxNoOfElements)viewModel.element += "6"
                        if (viewModel.equalClicked) viewModel.equalClicked = false},
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    modifier = Modifier
                        .size(ButtonSize),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(NumButtonColor),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 0.dp
                    )
                ) {

                    Text(
                        text = "6",
                        color = ButtonTextColor,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                }


                Button(
                    onClick = { if (viewModel.element.length < maxNoOfElements)viewModel.element += "3"
                        if (viewModel.equalClicked) viewModel.equalClicked = false},
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    modifier = Modifier
                        .size(ButtonSize),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(NumButtonColor),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 0.dp
                    )
                ) {

                    Text(
                        text = "3",
                        color = ButtonTextColor,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                }


                Button(
                    onClick = { if (viewModel.element.length < maxNoOfElements)viewModel.element += "."
                        if (viewModel.equalClicked) viewModel.equalClicked = false},
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    modifier = Modifier
                        .size(ButtonSize),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(ButtonColor),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 0.dp
                    )
                ) {

                    Text(
                        text = ".",
                        color = ButtonTextColor,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                }


            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(bottom = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                if (!viewModel.swapState) {
                    Button(
                        onClick = {
                            if (viewModel.element.length < maxNoOfElements){
                                if (viewModel.inverseState){
                                    viewModel.element += "tan("
                                }else {
                                    viewModel.element += "atan("
                                }
                            }

                            if (viewModel.equalClicked) viewModel.equalClicked = false
                        },
                        contentPadding = PaddingValues(horizontal = 0.dp),
                        modifier = Modifier
                            .size(scaleButton.value),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(ButtonColor),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 8.dp,
                            pressedElevation = 0.dp
                        )
                    ) {
                        Row {
                            Text(
                                text = "tan",
                                color = ButtonTextColor,
                                fontSize = 21.sp,
                                fontWeight = FontWeight.Medium
                            )
                            if (!viewModel.inverseState) {
                                Text(
                                    text = "-1",
                                    color = ButtonTextColor,
                                    fontSize = 10.sp,
                                )
                            }
                        }
                    }
                }


                Button(
                    onClick = { if (viewModel.element.length < maxNoOfElements)viewModel.element += "/"
                        if (viewModel.equalClicked) viewModel.equalClicked = false},
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    modifier = Modifier
                        .size(ButtonSize),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(ButtonColor),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 0.dp
                    )
                ) {

                    Text(
                        text = "/",
                        color = ButtonTextColor,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                }


                Button(
                    onClick = { if (viewModel.element.length < maxNoOfElements)viewModel.element += "*"
                        if (viewModel.equalClicked) viewModel.equalClicked = false},
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    modifier = Modifier
                        .size(ButtonSize),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(ButtonColor),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 0.dp
                    )
                ) {

                    Text(
                        text = "*",
                        color = ButtonTextColor,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Button(
                    onClick = { if (viewModel.element.length < maxNoOfElements)viewModel.element += "-"
                        if (viewModel.equalClicked) viewModel.equalClicked = false},
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    modifier = Modifier
                        .size(ButtonSize),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(ButtonColor),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 0.dp
                    )
                ) {

                    Text(
                        text = "-",
                        color = ButtonTextColor,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                }


                Button(
                    onClick = { if (viewModel.element.length < maxNoOfElements)viewModel.element += "+"
                        if (viewModel.equalClicked) viewModel.equalClicked = false},
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    modifier = Modifier
                        .size(ButtonSize),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(ButtonColor),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 0.dp
                    )
                ) {

                    Text(
                        text = "+",
                        color = ButtonTextColor,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                }


                Button(
                    onClick = {
                        viewModel.element = viewModel.answer
                        viewModel.equalAnsStore = viewModel.answer
                        viewModel.equalClicked = true
                              },
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    modifier = Modifier
                        .size(ButtonSize),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 0.dp
                    )
                ) {

                    Text(
                        text = "=",
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                }


            }


        }
    }
}

@Composable
fun normKeyPad(ButtonSize: Dp = 75.dp) {

    val viewModel: sharedStateViewModel = viewModel()

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 6.dp, start = 12.dp, end = 12.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(bottom = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(ButtonSize),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(ButtonColor),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 0.dp
                )
            ) {

                Text(
                    text = "(",
                    color = ButtonTextColor,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }


            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(ButtonSize),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(ButtonColor),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 0.dp
                )
            ) {

                Text(
                    text = "7",
                    color = ButtonTextColor,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(ButtonSize),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(ButtonColor),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 0.dp
                )
            ) {

                Text(
                    text = "4",
                    color = ButtonTextColor,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }


            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(ButtonSize),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(ButtonColor),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 0.dp
                )
            ) {

                Text(
                    text = "1",
                    color = ButtonTextColor,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }


            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(ButtonSize),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(ButtonColor),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 0.dp
                )
            ) {

                Text(

                    text = if (viewModel.swapState) "00"
                    else "e",
                    color = ButtonTextColor,
                    fontSize = if (viewModel.swapState) 19.sp
                    else 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }

        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(bottom = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(ButtonSize),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(ButtonColor),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 0.dp
                )
            ) {

                Text(
                    text = ")",
                    color = ButtonTextColor,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }


            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(ButtonSize),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(ButtonColor),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 0.dp
                )
            ) {

                Text(
                    text = "8",
                    color = ButtonTextColor,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(ButtonSize),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(ButtonColor),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 0.dp
                )
            ) {

                Text(
                    text = "5",
                    color = ButtonTextColor,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }


            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(ButtonSize),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(ButtonColor),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 0.dp
                )
            ) {

                Text(
                    text = "2",
                    color = ButtonTextColor,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }


            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(ButtonSize),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(ButtonColor),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 0.dp
                )
            ) {

                Text(
                    text = "0",
                    color = ButtonTextColor,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }


        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(bottom = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(ButtonSize),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(ButtonColor),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 0.dp
                )
            ) {

                Text(
                    text = "%",
                    color = ButtonTextColor,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }


            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(ButtonSize),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(ButtonColor),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 0.dp
                )
            ) {

                Text(
                    text = "9",
                    color = ButtonTextColor,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(ButtonSize),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(ButtonColor),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 0.dp
                )
            ) {

                Text(
                    text = "6",
                    color = ButtonTextColor,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }


            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(ButtonSize),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(ButtonColor),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 0.dp
                )
            ) {

                Text(
                    text = "3",
                    color = ButtonTextColor,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }


            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(ButtonSize),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(ButtonColor),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 0.dp
                )
            ) {

                Text(
                    text = ".",
                    color = ButtonTextColor,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }


        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(bottom = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(ButtonSize),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(ButtonColor),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 0.dp
                )
            ) {

                Text(
                    text = "/",
                    color = ButtonTextColor,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }


            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(ButtonSize),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(ButtonColor),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 0.dp
                )
            ) {

                Text(
                    text = "*",
                    color = ButtonTextColor,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(ButtonSize),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(ButtonColor),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 0.dp
                )
            ) {

                Text(
                    text = "-",
                    color = ButtonTextColor,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }


            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(ButtonSize),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(ButtonColor),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 0.dp
                )
            ) {

                Text(
                    text = "+",
                    color = ButtonTextColor,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }


            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(ButtonSize),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(ButtonColor),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 0.dp
                )
            ) {

                Text(
                    text = "=",
                    color = ButtonTextColor,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }


        }


    }
}
