package com.bosta.ahmedkhaled.data.repository

import com.bosta.ahmedkhaled.data.model.response.GetUsersResponse
import com.bosta.ahmedkhaled.data.remote.ApiInterface
import com.bosta.ahmedkhaled.domain.repository.UsersApIRepo
import javax.inject.Inject

class UsersApiRepoImpl @Inject constructor(private val api: ApiInterface): UsersApIRepo {
    override suspend fun getUsers(): MutableList<GetUsersResponse> {
        return api.getUsersApi()
    }
}