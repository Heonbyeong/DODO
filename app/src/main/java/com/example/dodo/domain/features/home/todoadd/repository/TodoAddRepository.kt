package com.example.dodo.domain.features.home.todoadd.repository

import com.example.dodo.domain.features.home.todoadd.SearchAddressParam
import com.example.dodo.domain.features.home.todoadd.entity.SearchAddressEntity

interface TodoAddRepository {

    suspend fun searchAddress(searchAddressParam: SearchAddressParam): SearchAddressEntity
}