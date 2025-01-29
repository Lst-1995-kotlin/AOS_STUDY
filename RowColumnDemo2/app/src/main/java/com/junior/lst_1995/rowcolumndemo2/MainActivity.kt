package com.junior.lst_1995.rowcolumndemo2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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

@Composable
fun MainScreen() {
    var scrollerState = rememberScrollState()
    Column(
        Modifier.verticalScroll(scrollerState)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TextCell(text = "1")
            TextCell(text = "2")
            TextCell(text = "3")
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TextCell(text = "4")
            TextCell(text = "5")
            TextCell(text = "6")
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TextCell(text = "7")
            TextCell(text = "8")
            TextCell(text = "9")
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            thickness = 2.dp,
            color = Color.Black
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            TextCell(text = "10", Modifier.align(Alignment.Top))
            TextCell(text = "11", Modifier.align(Alignment.CenterVertically))
            TextCell(text = "12", Modifier.align(Alignment.Bottom))
        }

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            thickness = 2.dp,
            color = Color.Black
        )

        Row {
            Text(
                text = "Large Text",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Small Text",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Row {
            Text(
                text = "Large Text\nMore Text",
                modifier = Modifier.alignByBaseline(),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Small Text",
                modifier = Modifier.alignByBaseline(),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Row {
            Text(
                text = "Large Text\nMore Text",
                modifier = Modifier.alignBy(LastBaseline),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Small Text",
                modifier = Modifier.alignByBaseline(),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Row {
            Text(
                text = "Large Text\nMore Text",
                modifier = Modifier.alignBy(LastBaseline),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Small Text",
                modifier = Modifier.paddingFrom(
                    alignmentLine = FirstBaseline,
                    before = 80.dp,
                    after = 0.dp
                ),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Row {
            TextCell(
                "1",
                Modifier
                    .weight(
                        weight = 0.2f,
                        fill = true,
                    )
            )
            TextCell(
                "2",
                Modifier
                    .weight(
                        weight = 0.3f,
                        fill = true,
                    )
            )
            TextCell(
                "3",
                Modifier
                    .weight(
                        weight = 0.5f,
                        fill = true,
                    )
            )
        }
    }
}

@Composable
fun TextCell(text: String, modifier: Modifier = Modifier) {
    val textModifier = modifier
        .size(100.dp, 100.dp)
        .padding(4.dp)
        .border(
            width = 4.dp, color = Color.Black
        )
        .wrapContentHeight(Alignment.CenterVertically)

    Text(
        text = text,
        modifier = textModifier.then(modifier),
        textAlign = TextAlign.Center,
        fontSize = 60.sp,
        fontWeight = FontWeight.Bold
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}