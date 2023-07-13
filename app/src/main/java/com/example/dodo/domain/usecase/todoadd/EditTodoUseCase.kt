package com.example.dodo.domain.usecase.todoadd

import com.example.dodo.domain.param.todo.EditTodoParam
import com.example.dodo.domain.repository.todo.TodoRepository
import com.example.dodo.domain.usecase.base.BaseUseCase
import javax.inject.Inject

class EditTodoUseCase @Inject constructor(
    private val todoRepository: TodoRepository
) : BaseUseCase<EditTodoParam, Unit>() {
    override suspend fun invoke(data: EditTodoParam) =
        todoRepository.editTodo(todo = data)
}