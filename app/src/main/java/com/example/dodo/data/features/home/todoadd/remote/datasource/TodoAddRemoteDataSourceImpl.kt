package com.example.dodo.data.features.home.todoadd.remote.datasource

import com.example.dodo.data.features.home.todoadd.remote.api.JusoApi
import com.example.dodo.data.features.home.todoadd.remote.request.SearchAddressRequest
import com.example.dodo.data.features.home.todoadd.remote.response.SearchAddressResponse
import javax.inject.Inject

class TodoAddRemoteDataSourceImpl @Inject constructor(
    private val jusoApi: JusoApi
) : TodoAddRemoteDataSource {
    override suspend fun searchAddress(searchAddressRequest: SearchAddressRequest): SearchAddressResponse =
        jusoApi.searchAddress(searchAddressRequest)

}