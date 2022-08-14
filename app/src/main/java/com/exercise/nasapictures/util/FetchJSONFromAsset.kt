package com.exercise.nasapictures.util

import android.content.Context
import com.exercise.nasapictures.model.NASAPicturesModel
import com.google.gson.Gson
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

        fun parseNASAPicturesJSON(context: Context, fileName: String) : NASAPicturesModel {
            return Gson().fromJson(readJSONFromAsset(context, fileName), NASAPicturesModel::class.java)
        }

    }
}