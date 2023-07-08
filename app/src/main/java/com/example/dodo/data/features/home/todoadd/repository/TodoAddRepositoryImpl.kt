package com.example.dodo.data.features.home.todoadd.repository

import com.example.dodo.data.features.home.todoadd.remote.datasource.TodoAddRemoteDataSource
import com.example.dodo.data.features.home.todoadd.remote.response.toEntity
import com.example.dodo.domain.entity.todoadd.SearchAddressEntity
import com.example.dodo.domain.repository.todoadd.TodoAddRepository
import javax.inject.Inject

class TodoAddRepositoryImpl @Inject constructor(
    private val todoAddRemoteDataSource: TodoAddRemoteDataSource
) : TodoAddRepository {

    override suspend fun searchAddress(
        confmKey: String,
        currentPage: Int,
        countPerPage: Int,
        keyword: String,
        resultType: String
    ): SearchAddressEntity =
        todoAddRemoteDataSource.searchAddress(
            confmKey = confmKey,
            currentPage = currentPage,
            countPerPage = countPerPage,
            keyword = keyword,
            resultType = resultType
        ).toEntity()
}