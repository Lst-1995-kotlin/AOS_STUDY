package com.junior.lst_1995.constraintlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

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
//    제약 이용하기
//    ConstraintLayout(Modifier.size(width = 200.dp, height = 200.dp)) {
//        val (button1, button2, button3) = createRefs()
//
//        MyButton(text = "Button 1", Modifier.constrainAs(button1) {
//            top.linkTo(parent.top, margin = 60.dp)
//            // 부모 안에서 자식 컴포넌트를 중앙에 배치
////            start.linkTo(parent.start)
////            end.linkTo(parent.end)
//
////            linkTo(parent.start, parent.end)
//
////            centerVerticallyTo(parent)
////            centerHorizontallyTo(parent)
//
//            // 컵포넌트들 사이에 제약 적용
////            centerHorizontallyTo(parent)
////            top.linkTo(parent.top)
////            bottom.linkTo(button2.top)
//            top.linkTo(parent.top, margin = 60.dp)
//            // bias 는 부모 폭의 몇% 위치에 배치할지 정한다.
//            // bias 는 기본으로 50% 로 되어있다.
////            linkTo(parent.start, parent.end, bias = 0.75f)
//            linkTo(parent.start, parent.end, startMargin = 30.dp ,endMargin = 50.dp)
//        })
////        MyButton(text = "Button 2", Modifier.constrainAs(button2) {
////            centerHorizontallyTo(parent)
////            top.linkTo(button1.bottom)
////            bottom.linkTo(parent.bottom)
////        })
//    }
//    체인 만들기
//    ConstraintLayout(Modifier.size(width = 400.dp, height = 100.dp)) {
//        val (button1, button2, button3) = createRefs()
//
//        createHorizontalChain(button1, button2, button3, chainStyle = ChainStyle.SpreadInside)
//
//        MyButton(text = "button 1", Modifier.constrainAs(button1) {
//            centerVerticallyTo(parent)
//        })
//
//        MyButton(text = "button 2", Modifier.constrainAs(button2) {
//            centerVerticallyTo(parent)
//        })
//
//        MyButton(text = "button 3", Modifier.constrainAs(button3) {
//            centerVerticallyTo(parent)
//        })
//    }
    // 가이드 라인 이용하기
//    ConstraintLayout(
//        Modifier
//            .size(width = 400.dp, height = 250.dp)
//            .background(Color.Gray)
//    ) {
//        val (button1, button2, button3) = createRefs()
//
//        val guide = createGuidelineFromStart(fraction = .60f)
//
//        MyButton(text = "Button1", Modifier.constrainAs(button1) {
//            top.linkTo(parent.top, margin = 30.dp)
//            end.linkTo(guide, margin = 30.dp)
//        })
//
//        MyButton(text = "Button2", Modifier.constrainAs(button2) {
//            top.linkTo(button1.bottom, margin = 20.dp)
//            start.linkTo(guide, margin = 40.dp)
//        })
//
//        MyButton(text = "Button3", Modifier.constrainAs(button3) {
//            top.linkTo(button2.bottom, margin = 40.dp)
//            end.linkTo(guide, margin = 20.dp)
//        })
//    }
    // 배리어 이용하기
//    ConstraintLayout(Modifier.size(width = 350.dp, height = 220.dp)) {
//        val (button1, button2, button3) = createRefs()
//
//        val barrier = createEndBarrier(button1, button2)
//
//        MyButton(text = "Button1", Modifier
//            .width(100.dp)
//            .constrainAs(button1) {
//                top.linkTo(parent.top, margin = 30.dp)
//                start.linkTo(parent.start, margin = 8.dp)
//            }
//        )
//        MyButton(text = "Button2", Modifier
//            .width(160.dp)
//            .constrainAs(button2) {
//                top.linkTo(button1.bottom, margin = 20.dp)
//                start.linkTo(parent.start, margin = 8.dp)
//            }
//        )
//
//        MyButton(text = "Button3", Modifier
//            .constrainAs(button3) {
//                linkTo(parent.top, parent.bottom, topMargin = 8.dp, bottomMargin = 8.dp)
//                linkTo(button1.end, parent.end, startMargin = 30.dp, endMargin = 8.dp)
//                start.linkTo(barrier, margin = 30.dp)
//                width = Dimension.fillToConstraints
//                height = Dimension.fillToConstraints
//            }
//        )
//    }
    // 제약 집합 사용하기
//    ConstraintLayout(Modifier.size(width = 200.dp, height = 200.dp)) {
//        val button1 = createRef()
//
//        MyButton(text = "Button1", Modifier.size(200.dp).constrainAs(button1){
//            linkTo(parent.top, parent.bottom, topMargin = 8.dp, bottomMargin = 8.dp)
//            linkTo(parent.start, parent.end, startMargin = 8.dp, endMargin = 8.dp)
//            width = Dimension.fillToConstraints
//            height = Dimension.fillToConstraints
//        })
//    }
    // 제약 들을 별도의 제약 집합으로 분리
    //
    ConstraintLayout(
        constraintSet = myConstraintSet(margin = 8.dp),
        Modifier.size(width = 200.dp, height = 200.dp)
    ) {
        MyButton(
            text = "Button1", Modifier
                .size(200.dp)
                .layoutId("button1")
        )
    }
}

// 제약 들을 별도의 제약 집합으로 분리
private fun myConstraintSet(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button1 = createRefFor("button1")
        constrain(button1) {
            linkTo(parent.top, parent.bottom, topMargin = margin, bottomMargin = margin)
            linkTo(parent.start, parent.end, startMargin = margin, endMargin = margin)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }
    }
}

@Composable
fun MyButton(text: String, modifier: Modifier = Modifier) {
    Button(
        onClick = { },
        modifier = modifier
    ) {
        Text(text)
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainScreen()
}