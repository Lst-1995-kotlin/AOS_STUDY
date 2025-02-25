package com.junior.lst_1995.gesturedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.junior.lst_1995.gesturedemo.ui.theme.GestureDemoTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GestureDemoTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        ClickDemo()
//        Spacer(Modifier.height(10.dp))
//        TapPressDemo()
//        Spacer(Modifier.height(10.dp))
//        DragDemo()
//        Spacer(Modifier.height(10.dp))
//        PointerInputDrag()
//    }
    //ScrollableModifier()
    //ScrollModifiers()
    MultiTouchDemo()
}

@Composable
fun MultiTouchDemo() {
    var scale by remember { mutableStateOf(1f) }
    var angle by remember { mutableStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    // zoomChange: 하나의 부동소수점값,꼬집기 제스처가 수행될 때 업데이트 된다.
    // panChange: 현재 x,y 오프셋값을 포함하는 하나의 Offset 인스턴스,
    // 제스처에 의해 대상 컴포넌트가 이동(트랜지션)할 때 업데이트 된다.
    // rotationChange: 하나의 부동소수점값, 회전 제스처를 감지했을 떄 현재 각도를 나타낸다.
    val state = rememberTransformableState { zoomChange, panChange, rotationChange ->
        scale *= zoomChange
        angle += rotationChange
        offset += panChange
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            Modifier
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    rotationZ = angle,
                    translationX = offset.x,
                    translationY = offset.y
                )
                .transformable(state = state)
                .background(Color.Blue)
                .size(100.dp)
        )
    }
}

@Composable
fun ScrollModifiers() {
    val image = ImageBitmap.imageResource(id = R.drawable.vacation)

    Box(
        modifier = Modifier
            .size(150.dp)
            .verticalScroll(rememberScrollState())
            .horizontalScroll(rememberScrollState())
    ) {
        Canvas(
            modifier = Modifier
                .size(360.dp, 270.dp)
        ) {
            drawImage(
                image = image,
                topLeft = Offset(x = 0f, y = 0f)
            )
        }
    }
}

@Composable
fun ScrollableModifier() {
    var offset by remember { mutableStateOf(0f) }
    Box(
        Modifier
            .fillMaxSize()
            .scrollable(
                orientation = Orientation.Vertical,
                state = rememberScrollableState { distance ->
                    offset += distance
                    distance
                }
            )
    ) {
        Box(
            modifier = Modifier
                .size(90.dp)
                .offset { IntOffset(0, offset.roundToInt()) }
                .background(Color.Red)
        )
    }
}


@Composable
fun PointerInputDrag() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        var xOffset by remember { mutableStateOf(0f) }
        var yOffset by remember { mutableStateOf(0f) }

        Box(
            Modifier
                .offset { IntOffset(xOffset.roundToInt(), yOffset.roundToInt()) }
                .background(Color.Blue)
                .size(100.dp)
                .pointerInput(Unit) {
                    detectDragGestures { _, distance ->
                        xOffset += distance.x
                        yOffset += distance.y
                    }
                }
        )
    }
}

@Composable
fun DragDemo() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        var xOffset by remember { mutableStateOf(0f) }

        Box(
            modifier = Modifier
                .offset { IntOffset(xOffset.roundToInt(), 0) }
                .size(100.dp)
                .background(Color.Blue)
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { distance ->
                        xOffset += distance
                    }
                )
        )
    }
}

@Composable
fun TapPressDemo() {
    var textState by remember { mutableStateOf("Waiting...") }
    val tapHandler = { string: String ->
        textState = string
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .background(Color.Blue)
                .size(100.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = { tapHandler("onPress Detected") },
                        onDoubleTap = { tapHandler("onDoubleTap Detected") },
                        onLongPress = { tapHandler("onLongPress Detected") },
                        onTap = { tapHandler("onTap Detected") }
                    )
                }
        )
        Spacer(Modifier.height(10.dp))
        Text(text = textState)
    }
}

@Composable
fun ClickDemo() {
    var colorState by remember { mutableStateOf(true) }
    var bgColor by remember { mutableStateOf(Color.Blue) }

    val clickHandler = {
        colorState = !colorState
        bgColor = if (colorState) Color.Blue else Color.DarkGray
    }
    Box(
        modifier = Modifier
            .clickable { clickHandler() }
            .background(bgColor)
            .size(100.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GestureDemoTheme {
        MainScreen()
    }
}