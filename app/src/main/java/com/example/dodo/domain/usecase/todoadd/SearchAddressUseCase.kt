package com.example.dodo.domain.usecase.todoadd

import com.example.dodo.BuildConfig
import com.example.dodo.domain.repository.todoadd.TodoAddRepository
import javax.inject.Inject

class SearchAddressUseCase @Inject constructor(
    private val todoAddRepository: TodoAddRepository
) {
    suspend operator fun invoke(
        confmKey: String = BuildConfig.ADDRESS_API_KEY,
        currentPage: Int = 1,
        countPerPage: Int = 10,
        keyword: String,
        resultType: String = "json"
    ) = todoAddRepository.searchAddress(
        confmKey = confmKey,
        currentPage = currentPage,
        countPerPage = countPerPage,
        keyword = keyword,
        resultType = resultType
    )
}