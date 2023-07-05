package com.example.dodo.data.features.home.todoadd.remote.response

import com.example.dodo.domain.features.home.todoadd.entity.SearchAddressEntity
import com.example.dodo.domain.features.home.todoadd.entity.SearchAddressEntity.*

data class SearchAddressResponse(
    val common: Common,
    val juso: Juso
)

fun SearchAddressResponse.toEntity() = SearchAddressEntity(
    common = this.common,
    juso = this.juso
)
