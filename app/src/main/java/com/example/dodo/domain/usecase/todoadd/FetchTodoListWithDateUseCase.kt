package com.example.dodo.domain.usecase.todoadd

import com.example.dodo.domain.entity.todo.TodoEntity
import com.example.dodo.domain.repository.todo.TodoRepository
import com.example.dodo.domain.usecase.base.BaseUseCase
import java.time.LocalDate
import javax.inject.Inject

class FetchTodoListWithDateUseCase @Inject constructor(
    private val todoRepository: TodoRepository
) : BaseUseCase<LocalDate, List<TodoEntity>>() {

    override suspend fun invoke(data: LocalDate): List<TodoEntity> =
        todoRepository.fetchTodoListWithDate(targetDate = data)
}