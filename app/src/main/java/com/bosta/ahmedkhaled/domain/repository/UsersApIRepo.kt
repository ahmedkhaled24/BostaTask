package com.bosta.ahmedkhaled.domain.repository

import com.bosta.ahmedkhaled.data.model.response.GetUsersResponse

interface UsersApIRepo {
    suspend fun getUsers(): MutableList<GetUsersResponse>
}