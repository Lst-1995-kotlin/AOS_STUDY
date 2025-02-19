package com.junior.lst1995.canvasdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.junior.lst1995.canvasdemo.ui.theme.CanvasDemoTheme
import kotlin.math.PI
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CanvasDemoTheme {
                DrawLine()
            }
        }
    }
}

@Composable
fun DrawLine() {
    Canvas(modifier = Modifier.size(300.dp)) {
        val height = size.height
        val width = size.width
        // 선그리기
//        drawLine(
//            start = Offset(x = 0f, y = 0f),
//            end = Offset(x = width, y = height),
//            color = Color.Blue,
//            strokeWidth = 16.0f
//        )
        // 점선 그리기
        // 선그리기는 PathEffect 인스턴스를 설정하고 그리기 함수를 호출할 때 PathEffect 인스턴스를
        // 전달하여 점선을 나타낼 수 있다.
        // 점선을 그릴 때는 PathEffect 인스턴스의 dashPathEffect() 메서드를 호출하고, 여기에 부동소수점 배열을 전달.
        // 부동 소주점 수는 선을 '그리는 구간'과 '그리지 않는 구간'을 픽셀 단위로 나타낸 것이며 해당 배열에는 값이
        // 최소 2개 이상 있어야 하며, 수는 짝수여야 한다.
        // 하기 코드는 시작점으로부터 30px의 실선, 10px의 공백, 10px의 실선, 10px의 공백 순서를 반복한다.
        // phase 는 대시 패턴이 시작되는 위치를 지정하는 값을 의미한다.
        drawLine(
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = width, y = height),
            color = Color.Blue,
            strokeWidth = 16.0f,
            pathEffect = PathEffect.dashPathEffect(
                floatArrayOf(30f, 10f, 10f, 10f), phase = 0f
            )
        )
    }
}

@Composable
fun DrawRect() {
    // 사각형 그리기
//    Canvas(modifier = Modifier.size(300.dp)) {
//        val size = Size(600f, 250f)
//        drawRect(
//            color = Color.Blue,
//            size = size
//        )
//    }
    // Canvas의 크기는 밀도 독립 픽셀(dp)로 선언되었고, 사각형의 크기는 픽셀(px)로 지정되었다.
    // 밀도 독립 픽셀은 인치당 도트 수(dpi)로 정의되는 화면의 물리적 밀도를 기반으로 계산되는 추상적인 측정값이다.
    // 반면 픽셍은 화면의 실제 물리적인 픽셀을 나타낸다. 오로지 픽셀만으로 작업할 때는 dp 값으로 시작한 뒤
    // 픽셀로 변환해야한다.
    // dp 값으로 시작하여 픽셀로 변환하여 사각형 그리기
//    Canvas(modifier = Modifier.size(300.dp)) {
//        val size = Size(200.dp.toPx(), 100.dp.toPx())
//        drawRect(
//            color = Color.Blue,
//            size = size
//        )
//    }

    Canvas(modifier = Modifier.size(300.dp)) {
        // 구체적인 수치 대신 Canvas 크기를 기준으로 사각형 크기를 상대적으로 정의하기
//        drawRect(
//            color = Color.Blue,
//            size = size / 2f
//        )
        // 사각형의 위치는 그림의 왼쪽 위 모서리(topLeft)의 좌표로 지정할 수 있다.
//        drawRect(
//            color = Color.Blue,
//            topLeft = Offset(x = 350f, y = 300f),
//            size = size / 2f
//        )
        // inset() 함수를 이용해 Canvas 컴포넌트의 경계를 수정할 수 있다.
//        inset(100f, 200f) {
//            drawRect(
//                color = Color.Blue,
//                size = size / 2f
//            )
//        }
        // drawRoundRect() 함수를 이용하며 모서리가 둥근 사각형을 그릴 수 있다.
        // 크기나 위치와 함께 적절하게 설정한 CornerRadius 컴포넌트를 전달해야 한다.
        // style 프로퍼티에 Stroke를 지정해 테두리만 가진 사각형(각진 모서리 또는 둥근 모서리)을 그릴 수 있다.
//        val size = Size(
//            width = 280.dp.toPx(),
//            height = 200.dp.toPx()
//        )
//
//        drawRoundRect(
//            color = Color.Blue,
//            size = size,
//            topLeft = Offset(20f, 20f),
//            style = Stroke(width = 8.dp.toPx()),
//            cornerRadius = CornerRadius(
//                x = 30.dp.toPx(),
//                y = 30.dp.toPx()
//            )
//        )
        // 회전 시키기
        rotate(45f) {
            drawRect(
                color = Color.Blue,
                topLeft = Offset(200f, 200f),
                size = size / 2f
            )
        }
    }
}

@Composable
fun DrawCircle() {
    Canvas(modifier = Modifier.size(300.dp)) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        // 원 그리기
//        drawCircle(
//            color = Color.Blue,
//            center = center,
//            radius = 120.dp.toPx()
//        )
        // 테두리만 있는 타원 그리기
        drawOval(
            color = Color.Blue,
            topLeft = Offset(x = 25.dp.toPx(), y = 90.dp.toPx()),
            size = Size(
                width = canvasWidth - 50.dp.toPx(),
                height = canvasHeight / 2 - 50.dp.toPx()
            ),
            style = Stroke(width = 12.dp.toPx())
        )
    }
}

@Composable
fun GradientFill() {
    Canvas(
        modifier = Modifier.size(300.dp)
    ) {
        val canvasSize = size
        val colorList: List<Color> = listOf(
            Color.Red,
            Color.Blue,
            Color.Magenta,
            Color.Yellow,
            Color.Green,
            Color.Cyan
        )

//        val brush = Brush.horizontalGradient(
//            colors = colorList,
//            startX = 0f,
//            endX = 300.dp.toPx(),
//            tileMode = TileMode.Repeated
//        )
        // 그레이디언트 사각형 그리기
//        drawRect(
//            brush = brush,
//            size = canvasSize
//        )
        // 그레이디언트 원 그리기
//        val radius = 150.dp.toPx()
//        val brush = Brush.radialGradient(
//            colors = colorList,
//            center = center,
//            radius = radius,
//            tileMode = TileMode.Repeated
//        )
//        drawCircle(
//            brush = brush,
//            center = center,
//            radius = radius,
//        )
        // 그림자 효과 추가
//        val radius = 150.dp.toPx()
//        val shadowColorList: List<Color> = listOf(Color.Blue, Color.Black)
//        val brush = Brush.horizontalGradient(
//            colors = shadowColorList,
//            startX = 0f,
//            endX = 300.dp.toPx(),
//            tileMode = TileMode.Repeated
//        )
//
//        drawCircle(
//            brush = brush,
//            radius = radius
//        )
        // 부채꼴 그리기
        // 250dp * 250dp 사각형 안에 시작각도 20도, 내각 90도인 부채꼴을 그린다.
        drawArc(
            Color.Blue,
            startAngle = 20f,
            sweepAngle = 90f,
            useCenter = true,
            size = Size(250.dp.toPx(), 250.dp.toPx())
        )
    }
}

@Composable
fun DrawPath() {
    // DrawScope를 이용하면 경로를 그릴 수 있다.
    // 경로는 캔버스 영역 안의 일련의 좌표들을 연결하는 선을 그린 것이다.
    // 경로는 Path 클래스 인스턴스에 저장되며, 정의된 경로를 drawPath() 함수에 전달하면
    // Canvas 위에 경로가 그려진다.
    // 경로를 디자인할 때는 moveTo() 함수를 호출하고  첫 번째 시작 지점을 정의한다.
    // 이후 lineTo() 혹은 relativeLineTo() 함수를 이용해 다음 위치로 선을 연결한다.
    // lineTo() 함수는 다음 x,y 좌표를 받으며 이는 Canvas의 왼쪽 위 모서리를 기준으로 한 상대 좌표값이다.
    // relativeLineTo() 이전 위치를 기준으로 하는 좌표를 받으며, 좌표는 양수 또는 음수가 될 수 있다.
    // Path 클래스는 3차 베지어 곡선, 2차 베지어 곡선을 포함해 곡선을 그리는 함수도 제공한다.
    // 경로를 완성했다면 반드시 close() 함수를 호출하여 그리기를 완료해야한다.

    // 직선과 2차 베지어 곡선을 조합하여 커스텀 도형 그리기
    Canvas(modifier = Modifier.size(300.dp)) {
        val path = Path().apply {
            moveTo(0f, 0f)
            quadraticBezierTo(50.dp.toPx(), 200.dp.toPx(), 300.dp.toPx(), 300.dp.toPx())
            lineTo(270.dp.toPx(), 100.dp.toPx())
            quadraticBezierTo(60.dp.toPx(), 80.dp.toPx(), 0f, 0f)
            close()
        }

        drawPath(
            path = path,
            color = Color.Blue
        )
    }
}

@Composable
fun DrawPoint() {
    // 점그리기
    // drawPoints() 함수를 이용하면 Offset 인스턴스 리스트로 지정한 위치마다 점을 찍을 수 있다.
    // drawPoints() 함수의 pointMode 파라미터를 이용하면 각 점을 개별적으로 찍을 것인지 또는
    // Lines/Polygon 모드를 이용해 선으로 연결할 것인지 제어할 수 있다.
    // drawPoints() 함수는 알고리즘 주도로 그리기를 할 때 매우 유용하며, 다음은 개별 점으로 sin 곡선을 그린다.
    Canvas(modifier = Modifier.size(300.dp)) {
        val height = size.height
        val width = size.width
        val points = mutableListOf<Offset>()

        for (x in 0..width.toInt()) {
            val y = (sin(x * (2f * PI / width))
                    * (height / 2) + (height / 2)).toFloat()
            points.add(Offset(x.toFloat(), y))
        }

        drawPoints(
            points = points,
            strokeWidth = 3f,
            pointMode = PointMode.Points,
            color = Color.Blue
        )
    }
}

@Composable
fun DrawImage() {
    // DrawImage 컴포저블은 리소스 이미지의 ImageBitmap 버전을 만들고, 캔버스 영역의 왼쪽위 모서리부터
    // 이미지를 위치를 설정한 Offset 인스턴스와 함께 drawImage() 함수에 전달한다.
    val image = ImageBitmap.imageResource(id = R.drawable.vacation)
    Canvas(
        modifier = Modifier
            .size(360.dp, 270.dp)
    ) {
//        drawImage(
//            image = image,
//            topLeft = Offset(x = 0f, y = 0f)
//        )

        // drawImage() 함수를 이용하면 색상 필터를 렌더링된 이미지에 적용할 수 있다.
        // 이때 ColorFilter 인스턴스를 이용하면, 색조(tint), 광량(lighting), 색상 매트릭스(color matrix), 혼합(blend) 설정 등을
        // 적용할 수 있다.
        drawImage(
            image = image,
            topLeft = Offset(x = 0f, y = 0f),
            colorFilter = ColorFilter.tint(
                color = Color(0XADFFAA2E),
                blendMode = BlendMode.ColorBurn
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DrawImagePreview() {
    CanvasDemoTheme {
        DrawImage()
    }
}

@Preview(showBackground = true)
@Composable
fun DrawPointPreview() {
    CanvasDemoTheme {
        DrawPoint()
    }
}

@Preview(showBackground = true)
@Composable
fun DrawPathPreview() {
    CanvasDemoTheme {
        DrawPath()
    }
}


@Preview(showBackground = true)
@Composable
fun GradientFillPreview() {
    CanvasDemoTheme {
        GradientFill()
    }
}


@Preview(showBackground = true)
@Composable
fun DrawCirclePreview() {
    CanvasDemoTheme {
        DrawCircle()
    }
}

@Preview(showBackground = true)
@Composable
fun DrawRectPreview() {
    CanvasDemoTheme {
        DrawRect()
    }
}


@Preview(showBackground = true)
@Composable
fun DrawLinePreview() {
    CanvasDemoTheme {
        DrawLine()
    }
}

