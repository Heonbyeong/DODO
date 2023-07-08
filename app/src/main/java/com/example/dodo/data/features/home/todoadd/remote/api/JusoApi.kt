package com.example.dodo.data.features.home.todoadd.remote.api

import com.example.dodo.data.features.home.todoadd.remote.response.SearchAddressResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface JusoApi {

    @GET("/addrlink/addrLinkApi.do")
    suspend fun searchAddress(
        @Query("confmKey") confmKey: String,
        @Query("currentPage") currentPage: Int,
        @Query("countPerPage") countPerPage: Int,
        @Query("keyword") keyword: String,
        @Query("resultType") resultType: String
    ) : SearchAddressResponse
}