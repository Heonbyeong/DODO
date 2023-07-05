package com.example.dodo.data.features.home.todoadd.remote.api

import com.example.dodo.data.features.home.todoadd.remote.request.SearchAddressRequest
import com.example.dodo.data.features.home.todoadd.remote.response.SearchAddressResponse
import retrofit2.http.POST

interface JusoApi {

    @POST("/addrlink/addrLinkApi.do")
    suspend fun searchAddress(
        searchAddressRequest: SearchAddressRequest
    ) : SearchAddressResponse
}