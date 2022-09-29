package com.jetpackcompose.gallery

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jetpackcompose.gallery.model.PicturesModel
import com.jetpackcompose.gallery.util.FetchJSONFromAsset
import kotlinx.coroutines.launch

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    var nasaListResponse  by mutableStateOf<List<PicturesModel>?>(null)
    private set
    fun getAllNASAData() {
        viewModelScope.launch {
            try {
                //Fetching data from json file
                nasaListResponse = FetchJSONFromAsset.parsePicturesJSON(context, "data.json")
            } catch (e: Exception) {
                Log.e("Error",e.toString())
            }
        }
    }

}