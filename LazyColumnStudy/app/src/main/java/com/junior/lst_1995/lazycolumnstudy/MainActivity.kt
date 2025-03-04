package com.junior.lst_1995.lazycolumnstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.junior.lst_1995.lazycolumnstudy.ui.theme.LazyColumnStudyTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazyColumnStudyTheme {
                MainScreen(
                    modifier = Modifier
                        .statusBarsPadding()
                        .fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var list by remember { mutableStateOf(emptyList<String>()) }
    var isClear by remember { mutableStateOf(false) }
    LaunchedEffect(isClear) {
        list = List<String>(10) { "item #${it + 1}" }
    }

    val state = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val addListener = {
        coroutineScope.launch {
            state.animateScrollToItem(list.size - 1)
        }
        list = list.plus("item #${list.size + 1}")
    }
    val deleteListener = {
        list = list.dropLast(1)
    }
    val initListener = {
        isClear = !isClear
    }

    Column(
        modifier = modifier
    ) {
        TitleScreen(title = "Wanted Challenge")
        ButtonScreen(
            addListener,
            deleteListener,
            initListener
        )
        Spacer(modifier = Modifier.height(4.dp))
        ListScreen(list, state)
    }
}

@Composable
fun ListScreen(
    list: List<String>,
    state: LazyListState
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = state
    ) {
        items(list) { item ->
            ListItemScreen(item)
        }
    }
}

@Composable
fun ListItemScreen(item: String) {
    Text(
        text = item,
        Modifier
            .height(60.dp)
            .padding(4.dp),
        style = MaterialTheme.typography.bodyLarge,
        fontSize = 48.sp
    )
}

@Composable
fun ButtonScreen(
    addListener: () -> Unit,
    deleteListener: () -> Unit,
    initListener: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(width = 120.dp, height = 80.dp)
                .background(Color.LightGray)
                .align(Alignment.CenterVertically)
                .clickable { addListener() },
        ) {
            Text(
                "추가",
                modifier = Modifier
                    .align(Alignment.Center),
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Box(
            modifier = Modifier
                .size(width = 120.dp, height = 80.dp)
                .background(Color.Red)
                .align(Alignment.CenterVertically)
                .clickable { deleteListener() }
        ) {
            Text(
                "삭제",
                modifier = Modifier
                    .align(Alignment.Center),
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Box(
            modifier = Modifier
                .size(width = 120.dp, height = 80.dp)
                .background(Color.Green)
                .align(Alignment.CenterVertically)
                .clickable { initListener() }
        ) {
            Text(
                "초기화",
                modifier = Modifier
                    .align(Alignment.Center),
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

@Composable
fun TitleScreen(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TitlePreview() {
    MainScreen()
}
