package com.example.dodo.domain.usecase.todoadd

import com.example.dodo.domain.param.todoadd.TodoAddParam
import com.example.dodo.domain.repository.todo.TodoRepository
import com.example.dodo.domain.usecase.base.BaseUseCase
import javax.inject.Inject

class AddTodoUseCase @Inject constructor(
    private val todoRepository: TodoRepository
) : BaseUseCase<TodoAddParam, Unit>() {

    override suspend operator fun invoke(data: TodoAddParam) =
        todoRepository.addTodo(todo = data)
}