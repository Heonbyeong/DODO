package com.example.dodo.domain.entity.todoadd

data class SearchAddressEntity(
    val results: ResultEntity
) {
    data class ResultEntity(
        val common: CommonEntity,
        val juso: List<JusoEntity>
    )

    data class CommonEntity(
        val totalCount: String,
        val currentPage: String,
        val countPerPage: String,
        val errorCode: String,
        val errorMessage: String,
    )

    data class JusoEntity(
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
