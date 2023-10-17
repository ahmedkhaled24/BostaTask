package com.bosta.ahmedkhaled.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bosta.ahmedkhaled.data.model.response.GetAlbumsResponse
import com.bosta.ahmedkhaled.domain.usecases.AlbumsUseCase
import com.bosta.ahmedkhaled.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(private val albumsUseCase: AlbumsUseCase) : ViewModel() {

    private val _albumsState: MutableLiveData<Resource<MutableList<GetAlbumsResponse>>> = MutableLiveData()
    val albumsData: LiveData<Resource<MutableList<GetAlbumsResponse>>> = _albumsState

    fun albumsData(userId: Int) {
        viewModelScope.launch {
            albumsUseCase(userId).collect {
                _albumsState.postValue(it)
            }
        }
    }

}