package com.example.dodo.domain.param

import com.example.dodo.BuildConfig

data class SearchAddressParam(
    val confmKey: String = BuildConfig.ADDRESS_API_KEY,
    val currentPage: Int = 1,
    val countPerPage: Int = 10,
    val keyword: String = "",
    val resultType: String = "json"
)
