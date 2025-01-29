package com.junior.lst_1995.rowcolumndemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}

@Composable
fun MainScreen() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TextCell(text = "1")
            TextCell(text = "2")
            TextCell(text = "3")
        }
        Row(
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
                .background(Color.DarkGray),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            TextCell(text = "4")
            TextCell(text = "5")
            TextCell(text = "6")
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            TextCell("7")
            Row (
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth(),
            ){
                TextCell("7-1")
                TextCell("7-2")
                TextCell("7-3")
            }
            Row (
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth(),
            ){
                TextCell("8")
                TextCell("9")
            }
            Row (
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth(),
            ){
                TextCell("10")
                TextCell("11")
            }
        }
        Row(modifier = Modifier
            .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ){
                TextCell("12")
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom
            ){
                TextCell("13")
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Top
            ){
                TextCell("14")
            }
        }
    }

}

@Composable
fun TextCell(text: String, modifier: Modifier = Modifier) {
    val cellModifier = Modifier
        .size(100.dp, 100.dp)
        .padding(4.dp)
        .border(width = 4.dp, color = Color.Black)
        .wrapContentHeight(
            align = Alignment.CenterVertically
        )
    Text(
        text = text,
        modifier = cellModifier.then(modifier),
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}
