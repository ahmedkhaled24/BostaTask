package com.bosta.ahmedkhaled.domain.repository

import com.bosta.ahmedkhaled.data.model.response.GetAlbumsResponse

interface AlbumsApIRepo {
    suspend fun getAlbums(userId: Int): MutableList<GetAlbumsResponse>
}