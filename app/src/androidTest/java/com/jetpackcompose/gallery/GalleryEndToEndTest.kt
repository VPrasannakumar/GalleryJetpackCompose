package com.jetpackcompose.gallery

import android.view.ViewGroup
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jetpackcompose.gallery.ui.Screen
import com.jetpackcompose.gallery.ui.screen.MainContent
import com.jetpackcompose.gallery.ui.theme.NASAPicturesComposeTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class GalleryEndToEndTest {

    lateinit var navController: TestNavHostController
    lateinit var sharedViewModel: SharedViewModel

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
  /*  @get:Rule
    val composeTestRule = createComposeRule()*/

    private fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.clearAndSetContent(content: @Composable () -> Unit) {
        (this.activity.findViewById<ViewGroup>(android.R.id.content)?.getChildAt(0) as? ComposeView)?.setContent(content)
            ?: this.setContent(content)
    }

    @ExperimentalAnimationApi
    @Before
    fun setUp(){
        /*navController= TestNavHostController(ApplicationProvider.getApplicationContext())*/

        sharedViewModel= SharedViewModel(ApplicationProvider.getApplicationContext())

        composeTestRule.clearAndSetContent {
            val navController = rememberNavController()
            NASAPicturesComposeTheme {
                NavHost(
                    navController =navController ,
                    startDestination = Screen.MainScreen.route){
                    composable(Screen.MainScreen.route){
                        MainContent(navController = navController, sharedViewModel =sharedViewModel )
                    }
                }
            }
        }
    }



    @Test
    fun launchMain() {

       /* // Start the app
        composeTestRule.setContent {
            MainContent(navController =navController , sharedViewModel = sharedViewModel)
        }*/
        composeTestRule.onNodeWithText("NASAPictures").assertDoesNotExist()
        composeTestRule.onNodeWithText("NASAPictures").assertIsDisplayed()
      //  composeTestRule.onRoot().performClick()

    }

   /* @Test
    fun navigateToDetailsTest(){
        composeTestRule.setContent {
            DetailScreen(position = 2, navController = navController, sharedViewModel = sharedViewModel)
        }

    }*/

}