package com.jetpackcompose.gallery.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

//Model class for NASA Pictures data
@Parcelize
data class PicturesModel(
    @SerializedName("copyright") @Expose var copyright : String?=null,
    @SerializedName("date") @Expose var date : String?=null,
    @SerializedName("explanation") @Expose var explanation : String?= null,
    @SerializedName("hdurl") @Expose var hdUrl : String?= null,
    @SerializedName("title") @Expose var title : String?=null,
    ): Parcelable
