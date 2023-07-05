package com.example.dodo.data.features.home.todoadd.remote.datasource

import com.example.dodo.data.features.home.todoadd.remote.request.SearchAddressRequest
import com.example.dodo.data.features.home.todoadd.remote.response.SearchAddressResponse


interface TodoAddRemoteDataSource {

    suspend fun searchAddress(searchAddressRequest: SearchAddressRequest): SearchAddressResponse
}