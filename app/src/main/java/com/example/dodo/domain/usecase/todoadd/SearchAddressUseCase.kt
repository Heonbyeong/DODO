package com.example.dodo.domain.usecase.todoadd

import com.example.dodo.domain.entity.todoadd.SearchAddressEntity
import com.example.dodo.domain.param.todoadd.SearchAddressParam
import com.example.dodo.domain.repository.todoadd.TodoAddRepository
import com.example.dodo.domain.usecase.base.BaseUseCase
import javax.inject.Inject

class SearchAddressUseCase @Inject constructor(
    private val todoAddRepository: TodoAddRepository
) : BaseUseCase<SearchAddressParam, SearchAddressEntity>() {

    override suspend fun invoke(data: SearchAddressParam): SearchAddressEntity =
        todoAddRepository.searchAddress(
            confmKey = data.confmKey,
            currentPage = data.currentPage,
            countPerPage = data.countPerPage,
            keyword = data.keyword,
            resultType = data.resultType
        )
}