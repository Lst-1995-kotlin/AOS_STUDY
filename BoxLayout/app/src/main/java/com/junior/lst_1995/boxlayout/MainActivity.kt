package com.junior.lst_1995.boxlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
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
    val height = 200.dp
    val width = 200.dp
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .verticalScroll(scrollState)
            .padding(4.dp)
    ) {
        Row {
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier.size(400.dp, 400.dp)
            ) {
                TextCell("1", Modifier.size(width = width, height = height))
                TextCell("2", Modifier.size(width = width, height = height))
                TextCell("3", Modifier.size(width = width, height = height))
            }
        }
        Divider()
        Box(
            modifier = Modifier.size(height = 90.dp, width = 290.dp)
        ) {
            Text("TopStart", Modifier.align(Alignment.TopStart))
            Text("TopCenter", Modifier.align(Alignment.TopCenter))
            Text("TopEnd", Modifier.align(Alignment.TopEnd))

            Text("CenterStart", Modifier.align(Alignment.CenterStart))
            Text("Center", Modifier.align(Alignment.Center))
            Text("CenterEnd", Modifier.align(Alignment.CenterEnd))

            Text("BottomStart", Modifier.align(Alignment.BottomStart))
            Text("BottomCenter", Modifier.align(Alignment.BottomCenter))
            Text("BottomEnd", Modifier.align(Alignment.BottomEnd))
        }

        Divider()

        Box(
            Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(Color.Blue)
        )

        Divider()

        Box(
            Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(Color.Blue)
        )
        Divider()
        Box(
            Modifier
                .size(200.dp)
                .clip(CutCornerShape(30.dp))
                .background(Color.Blue)
        )

    }

}

@Composable
fun Divider(
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    thickness: Dp = 2.dp
) {
    val dividerModifier = Modifier
        .padding(vertical = 4.dp)
        .height(thickness)
        .background(color)
    HorizontalDivider(
        modifier = dividerModifier.then(modifier)
    )
}

@Composable
fun TextCell(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: Int = 150
) {
    val cellModifier = Modifier
        .padding(4.dp)
        .border(
            width = 5.dp,
            color = Color.Black
        )
    Surface {
        Text(
            text = text,
            modifier = cellModifier.then(modifier),
            fontWeight = FontWeight.Bold,
            fontSize = fontSize.sp,
            textAlign = TextAlign.Center
        )
    }
}