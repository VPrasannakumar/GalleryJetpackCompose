package com.exercise.nasapictures.util

import android.content.Context
import com.exercise.nasapictures.model.NASAPicturesModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream

class FetchJSONFromAsset {

    companion object {

        fun readJSONFromAsset(context: Context, fileName: String): String? {
            var json: String? = null
            try {
                val  inputStream: InputStream = context.assets.open(fileName)
                json = inputStream.bufferedReader().use{it.readText()}
            } catch (ex: Exception) {
                ex.printStackTrace()
                return null
            }
            return json
        }

        fun parseNASAPicturesJSON(context: Context, fileName: String) : List<NASAPicturesModel> {
            val gson = Gson()
            val listPersonType = object : TypeToken<List<NASAPicturesModel>>() {}.type
            return gson.fromJson(readJSONFromAsset(context, fileName), listPersonType)
        }

    }
}