package com.bosta.ahmedkhaled.data.repository

import com.bosta.ahmedkhaled.data.model.response.GetAlbumsResponse
import com.bosta.ahmedkhaled.data.remote.ApiInterface
import com.bosta.ahmedkhaled.domain.repository.AlbumsApIRepo
import javax.inject.Inject

class AlbumsApiRepoImpl @Inject constructor(private val api: ApiInterface): AlbumsApIRepo {
    override suspend fun getAlbums(userId: Int): MutableList<GetAlbumsResponse> {
        return api.getAlbumsApi(userId)
    }
}