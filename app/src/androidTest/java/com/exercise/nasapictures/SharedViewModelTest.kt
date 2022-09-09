package com.exercise.nasapictures

import android.app.Application
import junit.framework.Assert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class SharedViewModelTest {

    @Mock
    private lateinit var context: Application

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    private val testDispatcher = StandardTestDispatcher()
    private val testCoroutineScope = TestScope(testDispatcher)

    // Subject under test
    private lateinit var sharedViewModel: SharedViewModel


    @Before
    fun setupSharedViewModel() {
        MockitoAnnotations.openMocks(this)
        Mockito.`when`(context.applicationContext).thenReturn(context)
        Dispatchers.setMain(testDispatcher)
        sharedViewModel = SharedViewModel(context)
    }

    @Test
    fun loadTasks_loading() {

        testCoroutineScope.launch {
            sharedViewModel.getAllNASAData()
            val nasaResponse = sharedViewModel.getAllNASAData()
            Assert.assertNotNull(nasaResponse)
            print("NASAPicture JSON : $nasaResponse")
        }


    }
}