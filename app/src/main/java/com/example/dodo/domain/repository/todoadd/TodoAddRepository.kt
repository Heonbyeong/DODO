package com.example.dodo.domain.repository.todoadd

import com.example.dodo.domain.entity.todoadd.SearchAddressEntity

interface TodoAddRepository {

    suspend fun searchAddress(
        confmKey: String,
        currentPage: Int,
        countPerPage: Int,
        keyword: String,
        resultType: String
    ): SearchAddressEntity
}