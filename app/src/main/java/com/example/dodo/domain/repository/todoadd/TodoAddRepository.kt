package com.example.dodo.domain.repository.todoadd

import com.example.dodo.domain.param.todoadd.SearchAddressParam
import com.example.dodo.domain.entity.todoadd.SearchAddressEntity

interface TodoAddRepository {

    suspend fun searchAddress(searchAddressParam: SearchAddressParam): SearchAddressEntity
}