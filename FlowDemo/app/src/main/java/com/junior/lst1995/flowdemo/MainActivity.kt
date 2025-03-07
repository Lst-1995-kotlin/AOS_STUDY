package com.junior.lst1995.flowdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.junior.lst1995.flowdemo.ui.theme.FlowDemoTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlin.system.measureTimeMillis

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlowDemoTheme {
                ScreenSetup()
            }
        }
    }
}


@Composable
fun ScreenSetup(viewModel: DemoViewModel = DemoViewModel()) {
    //MainScreen1(viewModel.myFlow)
    //MainScreen2(viewModel.myFlow2)
    MainScreen3(viewModel)
}

@Composable
fun MainScreen1(flow: Flow<Int>){
    val count by flow.collectAsState(initial = 0)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Count: $count")
    }
}

@Composable
fun MainScreen2(flow: Flow<String>){
//    val count by flow.collectAsState(initial = "Current value =")
    var count by remember { mutableStateOf("Current value =") }

    LaunchedEffect(Unit) {
//        flow.collect {
//            count = it
//        }
        val elapsedTime = measureTimeMillis {
            flow
                .buffer()
                .collect {
                count = it
                delay(1000)
            }
        }
        count = "Duration = $elapsedTime"
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = count)
    }
}

@Composable
fun MainScreen3(viewModel: DemoViewModel){
    //val count by viewModel.sharedFlow.collectAsState(initial = 0)
    val count by viewModel.hotFlow.collectAsState(initial = 0)
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$count", style = TextStyle(fontSize = 40.sp))
        Button(
            onClick = {
                viewModel.startSharedFlow()
            }
        ) {
            Text("Click Me")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FlowDemoTheme {
        ScreenSetup(DemoViewModel())
    }
}