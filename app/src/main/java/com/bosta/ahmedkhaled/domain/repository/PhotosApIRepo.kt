package com.bosta.ahmedkhaled.domain.repository

import com.bosta.ahmedkhaled.data.model.response.GetPhotosResponse

interface PhotosApIRepo {
    suspend fun getPhotos(albumId: Int): MutableList<GetPhotosResponse>
}