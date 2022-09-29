package com.jetpackcompose.gallery.util

import android.content.Context
import com.jetpackcompose.gallery.model.PicturesModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream

class FetchJSONFromAsset {

    companion object {

        private fun readJSONFromAsset(context: Context, fileName: String): String? {
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


        fun parsePicturesJSON(context: Context, fileName: String) : List<PicturesModel> {
            val gson = Gson()
            val listPersonType = object : TypeToken<List<PicturesModel>>() {}.type
            return gson.fromJson(readJSONFromAsset(context, fileName), listPersonType)
        }

    }
}