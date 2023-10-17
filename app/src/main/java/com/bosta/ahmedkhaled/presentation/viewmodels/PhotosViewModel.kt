package com.bosta.ahmedkhaled.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bosta.ahmedkhaled.data.model.response.GetPhotosResponse
import com.bosta.ahmedkhaled.domain.usecases.PhotosUseCase
import com.bosta.ahmedkhaled.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(private val photosUseCase: PhotosUseCase) : ViewModel() {

    private val _photosState: MutableLiveData<Resource<MutableList<GetPhotosResponse>>> = MutableLiveData()
    val photoData: LiveData<Resource<MutableList<GetPhotosResponse>>> = _photosState


    fun usersData(albumId: Int) {
        viewModelScope.launch {
            photosUseCase(albumId).collect {
                _photosState.postValue(it)
            }
        }
    }
}