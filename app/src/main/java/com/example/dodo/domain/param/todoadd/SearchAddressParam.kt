package com.example.dodo.domain.param.todoadd

import com.example.dodo.BuildConfig

data class SearchAddressParam(
    val confirmKey: String = BuildConfig.ADDRESS_API_KEY,
    val currentPage: Int,
    val countPerPage: Int,
    val keyword: String,
    val resultType: String = "json"
)
