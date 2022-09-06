package com.exercise.nasapictures

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.exercise.nasapictures.model.NASAPicturesModel
import com.exercise.nasapictures.util.FetchJSONFromAsset
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    var nasaListResponse : List<NASAPicturesModel> by mutableStateOf(listOf())


    fun getAllNASAData() {
        viewModelScope.launch {

            try {
                nasaListResponse = FetchJSONFromAsset.parseNASAPicturesJSON(context, "data.json")

            } catch (e: Exception) {
                Log.e("Error",e.toString())
            }
        }
    }

}