package com.bosta.ahmedkhaled.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bosta.ahmedkhaled.data.model.response.GetUsersResponse
import com.bosta.ahmedkhaled.domain.usecases.UsersUseCase
import com.bosta.ahmedkhaled.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val usersUseCase: UsersUseCase) : ViewModel() {

    private val _usersState: MutableLiveData<Resource<MutableList<GetUsersResponse>>> = MutableLiveData()
    val usersData: LiveData<Resource<MutableList<GetUsersResponse>>> = _usersState

    init {
        usersData()
    }

    private fun usersData() {
        viewModelScope.launch {
            usersUseCase().collect {
                _usersState.postValue(it)
            }
        }
    }
}