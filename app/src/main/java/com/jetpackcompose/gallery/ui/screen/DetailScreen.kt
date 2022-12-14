package com.jetpackcompose.gallery.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.jetpackcompose.gallery.SharedViewModel
import com.jetpackcompose.gallery.model.PicturesModel
import com.jetpackcompose.gallery.ui.Screen
import com.google.accompanist.pager.*
import com.jetpackcompose.gallery.R
import com.jetpackcompose.gallery.ui.theme.PicturesComposeTheme
import com.jetpackcompose.gallery.ui.theme.Purple700
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailScreen(
    position: Int,
    navController: NavController,
    sharedViewModel: SharedViewModel
) {

    //Array of Pictures data
    val listOfNASADetails = sharedViewModel.nasaListResponse
    Log.v("DetailScreen","position - "+position)
    listOfNASADetails.let {
        Log.v("DetailScreen","position - "+listOfNASADetails!![position].title)
    }

    PicturesComposeTheme {
        // background color for our application
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
                    val state = rememberPagerState(position)
                    Column {
                        // Initiate slider with postion and array of objects
                        SliderView(state, listOfNASADetails)
                    }
                    //Delay between pages 5secs
                    LaunchedEffect(key1 = state.currentPage) {
                        delay(5000)
                        var newPosition = state.currentPage + 1
                        if (newPosition > listOfNASADetails!!.size - 1) newPosition = 0
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
fun SliderView(state: PagerState, listOfNASADetails: List<PicturesModel>?) {

    val imageUrl = remember { mutableStateOf("") }
    HorizontalPager(
        state = state,
        count = listOfNASADetails!!.size, modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) { page ->
        imageUrl.value = listOfNASADetails[page].hdUrl!!
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //Full screen image
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

                // Details about image
                Column(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(5.dp)
                        .background(Color.LightGray.copy(alpha = 0.60F))) {

                    listOfNASADetails[page].title?.let {
                        Text(
                            text = it,
                            Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(2.dp),
                            textAlign = TextAlign.Start,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }


                    listOfNASADetails[page].explanation?.let {
                        Text(
                            text = it,
                            Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(2.dp),
                            textAlign = TextAlign.Start,
                            maxLines = 2,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Spacer(modifier = Modifier.padding(5.dp))
                    listOfNASADetails[page].date?.let {
                        Text(
                            text = it,
                            Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(2.dp),
                            textAlign = TextAlign.End,
                            maxLines = 2,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    listOfNASADetails[page].copyright?.let {
                        Text(
                            text = it,
                            Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(2.dp),
                            textAlign = TextAlign.End,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }


        }
    }

}
