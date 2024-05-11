package com.example.scientificcalculator

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawerBody(
    items : List<MenuItems>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 46.sp),
    onItemClick : (MenuItems) -> Unit
){
    LazyColumn(modifier) {
    items(items) {item ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onItemClick(item)
                }
                .padding(16.dp)

        ) {

            Icon(
                imageVector = item.icon,
                contentDescription = item.contentDescription
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = item.name,
                modifier = Modifier.weight(1f)
            )
        }
    }



    }

}