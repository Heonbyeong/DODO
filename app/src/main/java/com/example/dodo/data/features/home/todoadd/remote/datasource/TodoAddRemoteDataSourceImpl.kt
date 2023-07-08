package com.example.dodo.data.features.home.todoadd.remote.datasource

import com.example.dodo.data.features.home.todoadd.remote.api.JusoApi
import com.example.dodo.data.features.home.todoadd.remote.response.SearchAddressResponse
import com.example.dodo.domain.base.ErrorHandler
import javax.inject.Inject

class TodoAddRemoteDataSourceImpl @Inject constructor(
    private val jusoApi: JusoApi,
    private val errorHandler: ErrorHandler
) : TodoAddRemoteDataSource {

    override suspend fun searchAddress(
        confmKey: String,
        currentPage: Int,
        countPerPage: Int,
        keyword: String,
        resultType: String
    ): SearchAddressResponse =
        errorHandler {
            jusoApi.searchAddress(
                confmKey = confmKey,
                currentPage = currentPage,
                countPerPage = countPerPage,
                keyword = keyword,
                resultType = resultType
            )
        }

}