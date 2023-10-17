package com.bosta.ahmedkhaled.data.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetAlbumsResponse(
    @Expose
    @SerializedName("userId")
    val userId: Int,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("title")
    val title: String
)