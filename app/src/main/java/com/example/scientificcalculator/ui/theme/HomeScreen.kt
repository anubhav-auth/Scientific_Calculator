package com.example.scientificcalculator.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    Box(
        modifier = Modifier
            .background(BackgroundCal)
            .fillMaxSize()
    ){
        Column {
            display()
            normKeyPad(ButtonSize = 70.dp)
        }

    }

}



@Composable
fun display() {
    var swapState by remember {
        mutableStateOf(false)
    }
    var angleUnitState by remember {
        mutableStateOf(false)
    }


        Card(
            modifier = Modifier
                .size(400.dp)
                .padding(top = 50.dp, start = 15.dp, end = 15.dp, bottom = 15.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(DisplayColor),
            elevation = CardDefaults.cardElevation(500.dp),
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    val height = constraints.maxHeight
                    val width = constraints.maxWidth
                    val gradient = Brush.linearGradient(
                        colors = listOf(DisplayColor, GradColor),
                        start = Offset(width * 0.3f, height * 0.7f),
                        end = Offset(width * 1f, height * 0f)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(brush = gradient)
                    )
                }

                Box(modifier = Modifier.fillMaxSize()) {
                    Icon(
                        Icons.Rounded.Menu,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(top = 13.dp, start = 22.dp)
                            .clickable { },
                        tint = Color.White,

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
                                        swapState = !swapState
                                    },
                                textAlign = TextAlign.Center,
                                text = if (swapState) "Sci"
                                else "Norm",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                modifier = Modifier
                                    .padding(top = 9.dp, end = 30.dp)
                                    .clickable { angleUnitState = !angleUnitState },
                                textAlign = TextAlign.End,
                                text = if (angleUnitState) "Deg"
                                else "Rad",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleLarge

                            )

                        }
                        Row(
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {

                            Text(
                                modifier = Modifier
                                    .padding(top = 9.dp, end = 30.dp)
                                    .clickable { },
                                textAlign = TextAlign.End,
                                text = "AC",
                                color = ClearOperator,
                                fontWeight = FontWeight.Normal,
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                modifier = Modifier
                                    .padding(top = 9.dp, end = 30.dp)
                                    .clickable { },
                                textAlign = TextAlign.End,
                                text = "C",
                                color = ClearOperator,
                                fontWeight = FontWeight.Normal,
                                style = MaterialTheme.typography.titleLarge

                            )

                        }
                    }
                }

            }

        }

}

@Composable
fun normKeyPad(ButtonSize: Dp = 10.dp) {
    Row (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 6.dp, start = 12.dp, end = 12.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(bottom = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(ButtonSize),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(ButtonColor),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp, pressedElevation = 0.dp)
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
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp, pressedElevation = 0.dp)
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
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp, pressedElevation = 0.dp)
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
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp, pressedElevation = 0.dp)
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
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp, pressedElevation = 0.dp)
            ) {

                Text(

                    text = "00",
                    color = ButtonTextColor,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Medium
                )
            }

        }


        Column (
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(bottom = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(ButtonSize),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(ButtonColor),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp, pressedElevation = 0.dp)
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
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp, pressedElevation = 0.dp)
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
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp, pressedElevation = 0.dp)
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
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp, pressedElevation = 0.dp)
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
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp, pressedElevation = 0.dp)
            ) {

                Text(
                    text = "0",
                    color = ButtonTextColor,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }








        }


        Column (
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(bottom = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(ButtonSize),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(ButtonColor),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp, pressedElevation = 0.dp)
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
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp, pressedElevation = 0.dp)
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
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp, pressedElevation = 0.dp)
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
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp, pressedElevation = 0.dp)
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
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp, pressedElevation = 0.dp)
            ) {

                Text(
                    text = ".",
                    color = ButtonTextColor,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            }








        }


        Column (
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(bottom = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(ButtonSize),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(ButtonColor),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp, pressedElevation = 0.dp)
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
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp, pressedElevation = 0.dp)
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
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp, pressedElevation = 0.dp)
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
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp, pressedElevation = 0.dp)
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
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp, pressedElevation = 0.dp)
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


@Composable
fun sciKeyPad(modifier: Modifier = Modifier) {
    
}