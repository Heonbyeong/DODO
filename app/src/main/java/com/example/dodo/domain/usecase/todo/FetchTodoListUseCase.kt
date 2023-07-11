package com.example.dodo.domain.usecase.todo

import com.example.dodo.domain.entity.todo.TodoEntity
import com.example.dodo.domain.repository.todo.TodoRepository
import com.example.dodo.domain.usecase.base.BaseUseCase
import javax.inject.Inject

class FetchTodoListUseCase @Inject constructor(
    private val todoRepository: TodoRepository
) : BaseUseCase<Unit, List<TodoEntity>>() {

    override suspend fun invoke(data: Unit): List<TodoEntity>  =
        todoRepository.fetchTodoList()

}