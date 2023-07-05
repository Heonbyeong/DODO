package com.example.dodo.domain.entity.todoadd

data class SearchAddressEntity(
    val common: Common,
    val juso: Juso
) {
    data class Common(
        val totalCount: String,
        val currentPage: String,
        val countPerPage: String,
        val errorCode: String,
        val errorMessage: String,
    )

    data class Juso(
        val roadAddr: String,
        val roadAddrPart1: String,
        val roadAddrPart2: String,
        val jibunAddr: String,
        val engAddr: String,
        val zipNo: String,
        val admCd: String,
        val rnMgtSn: String,
        val bdMgtSn: String,
        val detBdNmList: String,
        val bdNm: String
    )
}
