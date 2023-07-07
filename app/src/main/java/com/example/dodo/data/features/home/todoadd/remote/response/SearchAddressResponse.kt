package com.example.dodo.data.features.home.todoadd.remote.response

import com.example.dodo.domain.entity.todoadd.SearchAddressEntity
import com.example.dodo.domain.entity.todoadd.SearchAddressEntity.*

data class SearchAddressResponse(
    val results: Result
) {
    data class Result(
        val common: Common,
        val juso: List<Juso>
    )
    data class Common(
        val totalCount: String = "",
        val currentPage: String = "",
        val countPerPage: String = "",
        val errorCode: String = "",
        val errorMessage: String = "",
    )

    data class Juso(
        val roadAddr: String = "",
        val roadAddrPart1: String = "",
        val roadAddrPart2: String = "",
        val jibunAddr: String = "",
        val engAddr: String = "",
        val zipNo: String = "",
        val admCd: String = "",
        val rnMgtSn: String = "",
        val bdMgtSn: String = "",
        val detBdNmList: String = "",
        val bdNm: String = ""
    )
}

fun SearchAddressResponse.toEntity() = SearchAddressEntity(
    results = this.results.toEntity(),
)

fun SearchAddressResponse.Result.toEntity() = ResultEntity(
    common = this.common.toEntity(),
    juso = this.juso.map { it.toEntity() }
)

fun SearchAddressResponse.Common.toEntity() = CommonEntity(
    totalCount = this.totalCount,
    currentPage = this.currentPage,
    countPerPage = this.countPerPage,
    errorCode = this.errorCode,
    errorMessage = this.errorMessage
)

fun SearchAddressResponse.Juso.toEntity() = JusoEntity(
    roadAddr = this.roadAddr,
    roadAddrPart1 = this.roadAddrPart1,
    roadAddrPart2 = this.roadAddrPart2,
    jibunAddr = this.jibunAddr,
    engAddr = this.engAddr,
    zipNo = this.zipNo,
    admCd = this.admCd,
    rnMgtSn = this.rnMgtSn,
    bdMgtSn = this.bdMgtSn,
    detBdNmList = this.detBdNmList,
    bdNm = this.bdNm
)