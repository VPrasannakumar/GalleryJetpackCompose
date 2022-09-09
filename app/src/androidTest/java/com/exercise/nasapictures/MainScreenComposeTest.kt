package com.exercise.nasapictures

import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.exercise.nasapictures.ui.Screen
import com.exercise.nasapictures.ui.screen.MainContent
import com.exercise.nasapictures.ui.theme.NASAPicturesComposeTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class MainScreenComposeTest {

    lateinit var navController: TestNavHostController
    lateinit var sharedViewModel: SharedViewModel

    /*@get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()*/
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp(){
        navController= TestNavHostController(ApplicationProvider.getApplicationContext())
        sharedViewModel= SharedViewModel(ApplicationProvider.getApplicationContext())
    }

  /*  private fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.clearAndSetContent(content: @Composable () -> Unit) {
        (this.activity.findViewById<ViewGroup>(android.R.id.content)?.getChildAt(0) as? ComposeView)?.setContent(content)
            ?: this.setContent(content)
    }*/

    @Test
    fun launchMain() {

        // Start the app
        composeTestRule.setContent {
            MainContent(navController =navController , sharedViewModel = sharedViewModel)
        }

        composeTestRule.onNodeWithText("NASAPictures").assertIsDisplayed()

    }

}