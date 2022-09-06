package com.exercise.nasapictures.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.exercise.nasapictures.MainViewModel
import com.exercise.nasapictures.R
import com.exercise.nasapictures.model.NASAPicturesModel
import com.exercise.nasapictures.ui.Screen
import com.exercise.nasapictures.ui.theme.Purple700
import com.google.accompanist.pager.*
import com.plcoding.navigationdrawercompose.ui.theme.NASAPicturesComposeTheme
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(name: String?, navController: NavController) {

    NASAPicturesComposeTheme {
        // background color for our application
        val mainViewModel: MainViewModel = viewModel()
        mainViewModel.getAllNASAData()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {

            Scaffold(
                // in scaffold we are specifying top bar.
                topBar = {
                    // inside top bar we are specifying background color.
                    TopAppBar(backgroundColor = Purple700,
                        // along with that we are specifying title for our top bar.
                        title = {
                            // in the top bar we are specifying tile as a text
                            Text(
                                // on below line we are specifying
                                // text to display in top app bar.
                                text = stringResource(R.string.details),

                                // on below line we are specifying
                                // modifier to fill max width.
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),

                                // on below line we are specifying text alignment.
                                textAlign = TextAlign.Left,

                                // on below line we are specifying color for our text.
                                color = Color.White
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    // Back to main screen
                                    //navController.popBackStack()
                                    navController.navigate(Screen.MainScreen.route) {
                                        popUpTo(Screen.MainScreen.route) {
                                            inclusive = true
                                        }
                                    }
                                },
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "back button"
                                )
                            }
                        }
                    )
                }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    /*Text(text = "Hello, $name")*/

                    val state = rememberPagerState()
                    Column {
                        SliderView(state, mainViewModel)
                    }
                    LaunchedEffect(key1 = state.currentPage) {
                        delay(5000)
                        var newPosition = state.currentPage + 1
                        if (newPosition > mainViewModel.nasaListResponse.size - 1) newPosition = 0
                        // scrolling to the new position.
                        state.animateScrollToPage(newPosition)
                    }
                }

            }


        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun SliderView(state: PagerState, viewModel: MainViewModel) {

    val imageUrl =
        remember { mutableStateOf("") }
    HorizontalPager(
        state = state,
        count = viewModel.nasaListResponse.size, modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) { page ->
        imageUrl.value = viewModel.nasaListResponse[page].hdUrl!!

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.BottomCenter) {

                val painter = rememberImagePainter(data = imageUrl.value, builder = {
                    placeholder(R.drawable.ic_launcher_foreground)
                    scale(Scale.FILL)
                })
                Image(
                    painter = painter, contentDescription = "", Modifier
                        .padding(5.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxSize(), contentScale = ContentScale.Crop
                )

                Column(Modifier.fillMaxWidth()
                    .wrapContentHeight()
                    .padding(5.dp)
                    .background(Color.LightGray.copy(alpha = 0.60F))) {

                    viewModel.nasaListResponse[page].title?.let {
                        Text(
                            text = it,
                            Modifier
                                .fillMaxWidth()
                                .wrapContentHeight().padding(2.dp),
                            textAlign = TextAlign.Start,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }


                    viewModel.nasaListResponse[page].explanation?.let {
                        Text(
                            text = it,
                            Modifier
                                .fillMaxWidth()
                                .wrapContentHeight().padding(2.dp),
                            textAlign = TextAlign.Start,
                            maxLines = 2,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Spacer(modifier = Modifier.padding(5.dp))
                    viewModel.nasaListResponse[page].copyright?.let {
                        Text(
                            text = it,
                            Modifier
                                .fillMaxWidth()
                                .wrapContentHeight().padding(2.dp),
                            textAlign = TextAlign.End,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }


        }
    }

}
