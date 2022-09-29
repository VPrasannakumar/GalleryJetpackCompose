package com.jetpackcompose.gallery.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jetpackcompose.gallery.R
import com.jetpackcompose.gallery.SharedViewModel
import com.jetpackcompose.gallery.ui.Screen
import com.jetpackcompose.gallery.ui.theme.Purple700
import com.jetpackcompose.gallery.ui.theme.PicturesComposeTheme


@Composable
fun MainContent(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {

    PicturesComposeTheme {

        // background color for our application
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            // on below line we are specifying theme as scaffold.
            Scaffold(
                // in scaffold we are specifying top bar.
                topBar = {
                    // inside top bar we are specifying background color.
                    TopAppBar(backgroundColor = Purple700,
                        title = {
                            // Specifying tile as a text
                            Text(
                                // text to display in top app bar.
                                text = stringResource(R.string.app_name),
                                // modifier to fill max width.
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                // Specifying text alignment.
                                textAlign = TextAlign.Left,
                                // Specifying color for our text.
                                color = Color.White
                            )
                        }
                    )
                }
            ) { padding ->
                //Adding grid view
                gridView(sharedViewModel, navController, Modifier.padding(padding))

            }

        }
    }

}


// on below line we are creating grid view function for loading our grid view.
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun gridView(sharedViewModel: SharedViewModel, navController: NavController, modifier: Modifier = Modifier) {
     // on below line we are creating and initializing our array list
    sharedViewModel.getAllNASAData()

    // vertical grid for creating a grid view.
    LazyVerticalGrid(
        // column count for our grid view.
        columns = GridCells.Fixed(2),
        //Adding padding from all sides to our grid view.
        modifier = Modifier.padding(10.dp),
    ) {
        // Displaying our items upto the size of the list.
        items(sharedViewModel.nasaListResponse!!.size) {
            // Creating a card for each item of our grid view.
            Card(
                // inside our grid view on below line we are
                // adding on click for each item of our grid view.
                onClick = {
                    // inside on click we are displaying the toast message.
                    //navController.navigate(Screen.DetailScreen.route)
                    navController.navigate(Screen.DetailScreen.withArgs(it))
                },
                //Adding padding from our all sides.
                modifier = Modifier.padding(5.dp),
                // Adding elevation for the card.
                elevation = 10.dp,
                shape = RoundedCornerShape(10.dp)
            ) {
                // on below line we are creating a column on below sides.
                Column(
                    // Filling the max size,max height and max width
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    // Horizontal alignment for our column
                    horizontalAlignment = Alignment.CenterHorizontally,
                    // Adding vertical arrangement for our column
                    verticalArrangement = Arrangement.Center
                ) {
                    // on below line we are creating image for our grid view item.
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(sharedViewModel.nasaListResponse!![it].hdUrl)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = sharedViewModel.nasaListResponse!![it].title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(150.dp)
                            .wrapContentWidth()
                    )
                }
            }

        }
    }
}

