package com.example.dodo.data.features.home.todoadd.repository

import com.example.dodo.data.features.home.todoadd.remote.datasource.TodoAddRemoteDataSource
import com.example.dodo.data.features.home.todoadd.remote.request.toRequest
import com.example.dodo.data.features.home.todoadd.remote.response.toEntity
import com.example.dodo.domain.param.todoadd.SearchAddressParam
import com.example.dodo.domain.entity.todoadd.SearchAddressEntity
import com.example.dodo.domain.repository.todoadd.TodoAddRepository
import javax.inject.Inject

class TodoAddRepositoryImpl @Inject constructor(
    private val todoAddRemoteDataSource: TodoAddRemoteDataSource
) : TodoAddRepository {
    override suspend fun searchAddress(searchAddressParam: SearchAddressParam): SearchAddressEntity =
        todoAddRemoteDataSource.searchAddress(searchAddressParam.toRequest()).toEntity()
}