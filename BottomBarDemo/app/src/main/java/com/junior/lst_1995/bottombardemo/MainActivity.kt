package com.junior.lst_1995.bottombardemo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.junior.lst_1995.bottombardemo.screens.Contacts
import com.junior.lst_1995.bottombardemo.screens.Favorites
import com.junior.lst_1995.bottombardemo.screens.Home
import com.junior.lst_1995.bottombardemo.ui.theme.BottomBarDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottomBarDemoTheme {
                MainScreen()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var title by remember { mutableStateOf("Home") }

    val titleChange = { text: String ->
        title = text
    }
    Scaffold(
        topBar = { TopAppBar(title = { Text(title) }) },
        content = { NavigationHost(navController = navController) },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                titleChange = titleChange
            )
        }
    )
}

@Composable
fun BottomNavigationBar(navController: NavHostController, titleChange: (String) -> Unit) {
    // BottomAppBar 컴포넌트를 사용하여 하단 내비게이션 바를 생성
    BottomAppBar {
        // 현재 백 스택 엔트리를 가져옴. 이는 NavHostController를 통해 현재 화면 상태를 추적하는 데 사용
        val backStackEntry by navController.currentBackStackEntryAsState()

        // 현재 라우트를 가져옴. 이를 통해 현재 표시되고 있는 화면의 경로를 확인
        val currentRoute = backStackEntry?.destination?.route

        // 예정된 내비게이션 항목들에 대해 반복하여 NavigationBarItem을 생성
        NavBarItems.BarItems.forEach { navItem ->
            NavigationBarItem(
                // 현재 라우트가 내비게이션 아이템의 라우트와 같은지를 비교하여 선택 상태를 설정
                selected = if (currentRoute == navItem.route) {
                    titleChange(navItem.title)
                    true
                } else false,

                // 내비게이션 아이템을 클릭했을 때의 동작을 정의
                onClick = {
                    // navController를 사용하여 지정된 경로로 내비게이션
                    navController.navigate(navItem.route) {
                        // 시작 목적지로 돌아가는 설정. 이 경우 상태를 저장
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // 동일한 목적지로 중복 내비게이션을 피하기 위해 launchSingleTop을 true로 설정
                        launchSingleTop = true
                        // 복귀할 때 저장된 상태를 복원하기 위한 설정
                        restoreState = true
                    }
                },

                // 아이콘을 구성하는 람다식
                icon = {
                    Icon(
                        imageVector = navItem.image, // 내비게이션 항목의 아이콘 이미지 벡터를 사용
                        contentDescription = navItem.title, // 해당 아이콘의 컨텐츠 설명을 설정
                        modifier = Modifier.size(40.dp)
                    )
                },

                // 라벨을 구성하는 람다식
//                label = {
//                    Text(text = navItem.title) // 내비게이션 항목의 제목을 라벨로 표시
//                }
            )
        }
    }
}

@Composable
fun NavigationHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = NavRoute.Home.route
    ) {
        composable(NavRoute.Home.route) {
            Home()
        }
        composable(NavRoute.Contacts.route) {
            Contacts()
        }
        composable(NavRoute.Favorites.route) {
            Favorites()
        }
    }
}
