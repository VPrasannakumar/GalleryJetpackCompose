package com.exercise.nasapictures.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//Model class for NASA Pictures data
data class NASAPicturesModel(
    @SerializedName("copyright") @Expose var copyright : String?=null,
    @SerializedName("date") @Expose var date : String?=null,
    @SerializedName("explanation") @Expose var explanation : String?= null,
    @SerializedName("hdurl") @Expose var hdUrl : String?= null,
    @SerializedName("title") @Expose var title : String?=null,
    )
