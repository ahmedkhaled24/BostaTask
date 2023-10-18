package com.bosta.ahmedkhaled.presentation.navigators

import com.bosta.ahmedkhaled.data.model.response.GetAlbumsResponse

interface AlbumsNavigator {
    fun clickOnAlbumItem(item: GetAlbumsResponse)
}