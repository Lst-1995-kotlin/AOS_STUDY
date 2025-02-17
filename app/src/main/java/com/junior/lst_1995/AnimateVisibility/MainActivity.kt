package com.junior.lst_1995.AnimateVisibility

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.junior.lst_1995.AnimateVisibility.ui.theme.AnimateVisibilityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimateVisibilityTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen() {
    var boxVisible by remember { mutableStateOf(true) }

    val onClick = { newState: Boolean ->
        boxVisible = newState
    }

    Column(
        Modifier.padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Row(
//            Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceEvenly
//        ) {
//            CustomButton(text = "Show", targetState = true, onClick = onClick)
//            CustomButton(text = "Hide", targetState = false, onClick = onClick)
//        }
//
//        Spacer(modifier = Modifier.height(20.dp))
        // 일반
//        if (boxVisible) {
//            Box(modifier = Modifier
//                .size(height = 200.dp, width = 200.dp)
//                .background(Color.Blue)
//            )
//        }
        // 애니메이션 적용
//        AnimatedVisibility(
//            boxVisible,
//            enter = fadeIn() + expandVertically(),
//            exit = slideOutVertically()
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(height = 200.dp, width = 200.dp)
//                    .background(Color.Blue)
//            )
//        }
        // 진입/이탈 애니메이션 적용
//        AnimatedVisibility(
//            visible = boxVisible,
//            enter = fadeIn(),
//            exit = slideOutVertically()
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(height = 200.dp, width = 200.dp)
//                    .background(Color.Blue)
//            )
//        }
        // 유지 시간을 제어
//        AnimatedVisibility(
//            visible = boxVisible,
//            enter = fadeIn(animationSpec = tween(durationMillis = 5000)),
//            exit = slideOutVertically()
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(height = 200.dp, width = 200.dp)
//                    .background(Color.Blue)
//            )
//        }
        // 박스가 표시될 때 대상 위치에 가까워짐에 따라 애니메이션이 점점 느려진다.
//        AnimatedVisibility(
//            visible = boxVisible,
//            enter = fadeIn(
//                animationSpec = tween(
//                    durationMillis = 5000,
//                    easing = LinearOutSlowInEasing
//                )
//            ),
//            exit = slideOutVertically()
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(height = 200.dp, width = 200.dp)
//                    .background(Color.Blue)
//            )
//        }
        // 배지어 곡선 안의 4개 위치에서 애니메이션 속도를 병경
//        AnimatedVisibility(
//            visible = boxVisible,
//            enter = fadeIn(
//                animationSpec = tween(
//                    durationMillis = 5000,
//                    easing = CubicBezierEasing(0f,1f,0.5f,1f)
//                )
//            ),
//            exit = slideOutVertically()
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(height = 200.dp, width = 200.dp)
//                    .background(Color.Blue)
//            )
//        }
        // 애니메이션 반복하기
//        AnimatedVisibility(
//            visible = boxVisible,
//            enter = fadeIn(
//                animationSpec = repeatable(10,
//                    animation = tween(durationMillis = 2000),
//                    repeatMode = RepeatMode.Reverse
//                )
//            ),
//            exit = slideOutVertically()
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(height = 200.dp, width = 200.dp)
//                    .background(Color.Blue)
//            )
//        }
        // 자식별로 각각 애니메이션 적용하기
        // fadeIn() + slideInVertically()를 조합한 것과 동일한 효과
//        AnimatedVisibility(
//            visible = boxVisible,
//            enter = fadeIn(animationSpec = tween(durationMillis = 5500)),
//            exit = fadeOut(animationSpec = tween(durationMillis = 5500))
//        ) {
//            Row {
//                Box(
//                    Modifier
//                        .size(width = 150.dp, height = 150.dp)
//                        .background(Color.Blue)
//                )
//                Spacer(Modifier.width(20.dp))
//                Box(
//                    Modifier
//                        .animateEnterExit(
//                            enter = slideInVertically(
//                                animationSpec = tween(durationMillis = 5500)
//                            ),
//                            exit = slideOutVertically(
//                                animationSpec = tween(durationMillis = 5500)
//                            )
//                        )
//                        .size(width = 150.dp, height = 150.dp)
//                        .background(Color.Red)
//                )
//            }
//        }
        // 모디파이어의 애니메이션만 이용하고 싶다면 부모인 AnimatedVisibility 인스턴스에서
        // enter, exit 설정은 각각 EnterTransition.None, ExitTransition.None으로 지정해야한다.
//        AnimatedVisibility(
//            visible = boxVisible,
//            enter = EnterTransition.None,
//            exit = ExitTransition.None
//        ) {
//            Row {
//                Box(
//                    Modifier
//                        .size(width = 150.dp, height = 150.dp)
//                        .background(Color.Blue)
//                )
//                Spacer(Modifier.width(20.dp))
//                Box(
//                    Modifier
//                        .animateEnterExit(
//                            enter = slideInVertically(
//                                animationSpec = tween(durationMillis = 5500)
//                            ),
//                            exit = slideOutVertically(
//                                animationSpec = tween(durationMillis = 5500)
//                            )
//                        )
//                        .size(width = 150.dp, height = 150.dp)
//                        .background(Color.Red)
//                )
//            }
//        }
        // 애니메이션 자동으로 시작하기
//        val state = remember { MutableTransitionState(true) }
//        state.apply { targetState = true }
//        AnimatedVisibility(
//            visibleState = state,
//            enter = fadeIn(
//                animationSpec = tween(5000)
//            ),
//            exit = slideOutVertically()
//        ) {
//            Row {
//                Box(
//                    Modifier
//                        .size(width = 150.dp, height = 150.dp)
//                        .background(Color.Blue)
//                )
//                Spacer(Modifier.width(20.dp))
//                Box(
//                    Modifier
//                        .animateEnterExit(
//                            enter = slideInVertically(
//                                animationSpec = tween(durationMillis = 5500)
//                            ),
//                            exit = slideOutVertically(
//                                animationSpec = tween(durationMillis = 5500)
//                            )
//                        )
//                        .size(width = 150.dp, height = 150.dp)
//                        .background(Color.Red)
//                )
//            }
//        }
        // 교차 페이딩 구현
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Crossfade(
                targetState = boxVisible,
                animationSpec = tween(5000)
            ) { visible ->
                when (visible) {
                    true -> CustomButton(
                        text = "Hide",
                        targetState = false,
                        onClick = onClick,
                        bgColor = Color.Red
                    )
                    false -> CustomButton(
                        text = "Show",
                        targetState = true,
                        onClick = onClick,
                        bgColor = Color.Magenta
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            AnimatedVisibility(
                visible = boxVisible,
                enter = fadeIn(animationSpec = tween(5500)),
                exit = fadeOut(animationSpec = tween(5500))
            ) {
                Box(
                    modifier = Modifier
                        .size(width = 200.dp, height = 200.dp)
                        .background(Color.Blue)
                )
            }
        }
    }
}

@Composable
fun CustomButton(
    text: String,
    targetState: Boolean,
    onClick: (Boolean) -> Unit,
    bgColor: Color = Color.Blue
) {
    Button(
        onClick = { onClick(targetState) },
        colors = ButtonDefaults.buttonColors(containerColor = bgColor, contentColor = Color.White)
    ) {
        Text(text)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    AnimateVisibilityTheme {
        MainScreen()
    }
}