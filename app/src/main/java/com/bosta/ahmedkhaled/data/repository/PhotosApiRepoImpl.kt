package com.bosta.ahmedkhaled.data.repository

import com.bosta.ahmedkhaled.data.model.response.GetPhotosResponse
import com.bosta.ahmedkhaled.data.remote.ApiInterface
import com.bosta.ahmedkhaled.domain.repository.PhotosApIRepo
import javax.inject.Inject

class PhotosApiRepoImpl @Inject constructor(private val api: ApiInterface): PhotosApIRepo {
    override suspend fun getPhotos(albumId: Int): MutableList<GetPhotosResponse> {
        return api.getPhotosApi(albumId)
    }
}