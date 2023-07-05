package com.example.dodo.data.features.home.todoadd.remote.request

import com.example.dodo.domain.features.home.todoadd.SearchAddressParam

data class SearchAddressRequest(
    val confirmKey: String,
    val currentPage: Int,
    val countPerPage: Int,
    val keyword: String,
    val resultType: String
)

fun SearchAddressParam.toRequest() = SearchAddressRequest(
    confirmKey = this.confirmKey,
    currentPage = this.currentPage,
    countPerPage = this.countPerPage,
    keyword = this.keyword,
    resultType = this.resultType
)
