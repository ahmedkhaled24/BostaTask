package com.bosta.ahmedkhaled.data.remote

import com.bosta.ahmedkhaled.data.model.response.GetAlbumsResponse
import com.bosta.ahmedkhaled.data.model.response.GetPhotosResponse
import com.bosta.ahmedkhaled.data.model.response.GetUsersResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {

    @GET("users")
    suspend fun getUsersApi()
    : MutableList<GetUsersResponse>


    @GET("albums")
    suspend fun getAlbumsApi(
        @Query("userId") userId: Int
    ): MutableList<GetAlbumsResponse>


    @GET("photos")
    suspend fun getPhotosApi(
        @Query("albumId") albumId: Int)
    : MutableList<GetPhotosResponse>

}