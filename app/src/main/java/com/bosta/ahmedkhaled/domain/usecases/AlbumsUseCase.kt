package com.bosta.ahmedkhaled.domain.usecases

import android.util.Log
import com.bosta.ahmedkhaled.domain.repository.AlbumsApIRepo
import com.bosta.ahmedkhaled.utils.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val TAG = "TagAlbumsUseCase"
class AlbumsUseCase @Inject constructor(private val repository: AlbumsApIRepo) {

    operator fun invoke(userId: Int) = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getAlbums(userId = userId)
            emit(Resource.Success(data = response))

        } catch (e: HttpException) {
            Log.d(TAG, "error HttpException: ${e.message}")
            Log.d(TAG, "error HttpException code: ${e.code()}")
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))

        } catch (e: IOException) {
            Log.d(TAG, "error IOException: ${e.message}")
            emit(Resource.Error("Couldn't reach server. Please check your internet connection"))
        }
    }
}