package com.makza.lifeonmars

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.makza.lifeonmars.ui.theme.LifeOnMarsTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LifeOnMarsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BottomNavigationWithSwipeScreen()
                }
            }
        }
    }
}

fun showToast(context: Context, msg:String){
    Toast.makeText(context,msg,Toast.LENGTH_LONG).show()
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun BottomNavigationWithSwipeScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val images = listOf(
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground)
    val pageState = rememberPagerState(pageCount = 4, initialPage = 0)
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Life on Mars"
                    )
                },
                actions = {
                    IconButton(onClick = {
                        showToast(context = context, "Go to Home Pressed!")
                    }) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home"
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                content = {
                    for (page in images.indices){
                        BottomNavigationItem(
                            selected = page == pageState.currentPage,
                            onClick = {
                                scope.launch {
                                    pageState.animateScrollToPage(page = page)
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Filled.Home,
                                    contentDescription = "Page $page"
                                )
                            },
                            selectedContentColor = Color.Magenta,
                            unselectedContentColor = Color.LightGray,
                            label = {
                                Text(
                                    text = "Page ${page + 1}"
                                )
                            }
                        )
                    }
                }
            )
        }
    ) {
        HorizontalPager(
            state = pageState
        ) { page ->
            Image(
                painter = painterResource(id = images[page]),
                contentDescription = "Screen image",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

