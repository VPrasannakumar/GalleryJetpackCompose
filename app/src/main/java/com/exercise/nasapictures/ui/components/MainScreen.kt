package com.exercise.nasapictures.ui.components

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.exercise.nasapictures.R
import com.exercise.nasapictures.model.NASAPicturesModel
import com.exercise.nasapictures.ui.Screen
import com.exercise.nasapictures.ui.theme.Purple500
import com.exercise.nasapictures.util.FetchJSONFromAsset

@Composable
fun MainContent(navController: NavController) {
    //Adding grid view

    gridView(LocalContext.current, navController)

}


// on below line we are creating grid view function for loading our grid view.
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun gridView(context: Context,navController: NavController) {
    // on below line we are creating and initializing our array list
    lateinit var picturesList: List<NASAPicturesModel>
    picturesList = FetchJSONFromAsset.parseNASAPicturesJSON(context, "data.json")

    // vertical grid for creating a grid view.
    LazyVerticalGrid(
        // column count for our grid view.
        columns = GridCells.Fixed(2),
        //Adding padding from all sides to our grid view.
        modifier = Modifier.padding(10.dp)
    ) {
        // Displaying our items upto the size of the list.
        items(picturesList.size) {
            // Creating a card for each item of our grid view.
            Card(
                // inside our grid view on below line we are
                // adding on click for each item of our grid view.
                onClick = {
                    // inside on click we are displaying the toast message.
                    Toast.makeText(context, picturesList[it].title, Toast.LENGTH_SHORT).show()
                    navController.navigate(Screen.DetailScreen.withArgs(picturesList[it].title!!))
                },

                //Adding padding from our all sides.
                modifier = Modifier.padding(5.dp),
                // Adding elevation for the card.
                elevation = 10.dp
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
                            .data(picturesList[it].hdUrl)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = stringResource(R.string.app_name),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .wrapContentSize()
                            .wrapContentHeight()
                            .wrapContentWidth()
                    )
                }
            }

        }
    }
}

