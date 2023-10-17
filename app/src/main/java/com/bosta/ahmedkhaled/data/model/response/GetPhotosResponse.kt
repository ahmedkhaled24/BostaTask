package com.bosta.ahmedkhaled.data.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetPhotosResponse(
    @Expose
    @SerializedName("albumId")
    val albumId: Int,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("title")
    val title: String,
    @Expose
    @SerializedName("url")
    val url: String,
    @Expose
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String
)

