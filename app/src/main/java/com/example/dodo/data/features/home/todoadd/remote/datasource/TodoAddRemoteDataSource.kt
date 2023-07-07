package com.example.dodo.data.features.home.todoadd.remote.datasource

import com.example.dodo.data.features.home.todoadd.remote.response.SearchAddressResponse


interface TodoAddRemoteDataSource {

    suspend fun searchAddress(
        confmKey: String,
        currentPage: Int,
        countPerPage: Int,
        keyword: String,
        resultType: String
    ): SearchAddressResponse
}