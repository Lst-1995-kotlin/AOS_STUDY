package com.junior.lst_1995.animatestate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring.DampingRatioHighBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.junior.lst_1995.animatestate.ui.theme.AnimateStateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimateStateTheme {
                RotationDemo()
            }
        }
    }
}

enum class BoxPosition {
    Start, End
}

@Composable
fun TransitionDemo() {
    var boxState by remember { mutableStateOf(BoxPosition.Start) }
    var screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val transition = updateTransition(targetState = boxState, label = "Color and Motion")
    val animatedColor: Color by transition.animateColor(
        transitionSpec = {
            tween(durationMillis = 4000)
        }
    ) { state ->
        when(state) {
            BoxPosition.Start -> Color.Red
            BoxPosition.End -> Color.Magenta
        }
    }

    val animatedOffset: Dp by transition.animateDp(
        transitionSpec = {tween(5000)}
    ) { state ->
        when(state) {
            BoxPosition.Start -> 0.dp
            BoxPosition.End -> screenWidth - 70.dp
        }
    }


    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .offset(x = animatedOffset, y = 20.dp)
                .size(70.dp)
                .background(animatedColor)
        )

        Spacer(Modifier.height(50.dp))

        Button(
            onClick = {
                boxState = when (boxState) {
                    BoxPosition.Start -> BoxPosition.End
                    BoxPosition.End -> BoxPosition.Start
                }
            },
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Move Box")
        }
    }

}


@Composable
fun MotionDemo() {
    var boxState by remember { mutableStateOf(BoxPosition.Start) }
    val boxSideLength = 70.dp
    var screenWidth = (LocalConfiguration.current.screenWidthDp.dp)
//    val animateOffset: Dp by animateDpAsState(
//        targetValue = when (boxState) {
//            BoxPosition.Start -> 0.dp
//            BoxPosition.End -> screenWidth - boxSideLength
//        },
//        animationSpec = tween(500)
//    )
//    // 스프링 효과 추가
//    val animateOffset: Dp by animateDpAsState(
//        targetValue = when (boxState) {
//            BoxPosition.Start -> 0.dp
//            BoxPosition.End -> screenWidth - boxSideLength
//        },
//        animationSpec = spring(dampingRatio = DampingRatioHighBouncy)
//    )

    // 스프링 효과 + 강도 추가
//    val animateOffset: Dp by animateDpAsState(
//        targetValue = when (boxState) {
//            BoxPosition.Start -> 0.dp
//            BoxPosition.End -> screenWidth - boxSideLength
//        },
//        animationSpec = spring(
//            dampingRatio = DampingRatioHighBouncy,
//            stiffness = StiffnessVeryLow
//        )
//    )
//    // 키프레임 명세
//    val animateOffset: Dp by animateDpAsState(
//        targetValue = when (boxState) {
//            BoxPosition.Start -> 0.dp
//            BoxPosition.End -> screenWidth - boxSideLength
//        },
//        // 애니메이션 전체 유지시간 1,000 밀리초
//        // 첫 번째 타임스탬프 10 밀리초의 애니메이션을 수행, 오프셋값은 100dp 여야함.
//        // 두 번째 타임스탬프 500 밀리초의 애니메이션을 수행, 오프셋값은 110dp 여야함.
//        // 세 번째 타임스탬프 700 밀리초의 애니메이션을 수행, 오프셋값은 200dp 여야함.
//        // 남은 300 밀리초 동안 나머지 애니메이션을 수행.
//        animationSpec = keyframes {
//            durationMillis = 1000
//            100.dp.at(10)
//            110.dp.at(500)
//            200.dp.at(700)
//        }
//    )

    // 키프레임 명세 + 애니메이셔 동작 설정
    val animateOffset: Dp by animateDpAsState(
        targetValue = when (boxState) {
            BoxPosition.Start -> 0.dp
            BoxPosition.End -> screenWidth - boxSideLength
        },
        animationSpec = keyframes {
            durationMillis = 1000
            100.dp.at(10).using(LinearEasing)
            110.dp.at(500).using(FastOutSlowInEasing)
            200.dp.at(700).using(LinearOutSlowInEasing)
        }
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .offset(x = animateOffset, y = 20.dp)
                .size(boxSideLength)
                .background(Color.Red)
        )

        Spacer(Modifier.height(50.dp))

        Button(
            onClick = {
                boxState = when (boxState) {
                    BoxPosition.Start -> BoxPosition.End
                    BoxPosition.End -> BoxPosition.Start
                }
            },
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Move Box")
        }
    }
}


enum class BoxColor {
    Red, Magenta
}

@Composable
fun ColorChangeDemo() {
    var colorState by remember { mutableStateOf(BoxColor.Red) }

    val animatedColor: Color by animateColorAsState(
        targetValue = when (colorState) {
            BoxColor.Red -> Color.Red
            BoxColor.Magenta -> Color.Magenta
        },
        animationSpec = tween(4500)
    )


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .padding(20.dp)
                .size(200.dp)
                .background(animatedColor)
        )

        Button(
            onClick = {
                colorState = when (colorState) {
                    BoxColor.Red -> BoxColor.Magenta
                    BoxColor.Magenta -> BoxColor.Red
                }
            },
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = "Change Color")
        }
    }
}

@Composable
fun RotationDemo() {
    var rotated by remember { mutableStateOf(false) }

    val angle by animateFloatAsState(
        targetValue = if (rotated) 360f else 0f,
        animationSpec = tween(durationMillis = 2500, easing = LinearEasing)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.propeller),
            contentDescription = "Propeller",
            modifier = Modifier
                .rotate(angle)
                .padding(10.dp)
                .size(300.dp)
        )

        Button(
            onClick = { rotated = !rotated },
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = "Rotate Propeller")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransitionDemoPreview() {
    AnimateStateTheme {
        TransitionDemo()
    }
}

@Preview(showBackground = true)
@Composable
fun MotionDemoPreview() {
    AnimateStateTheme {
        MotionDemo()
    }
}

@Preview(showBackground = true)
@Composable
fun ColorChangeDemoPreview() {
    AnimateStateTheme {
        ColorChangeDemo()
    }
}

@Preview(showBackground = true)
@Composable
fun RotationDemoPreview() {
    AnimateStateTheme {
        RotationDemo()
    }
}



