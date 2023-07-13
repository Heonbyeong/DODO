package com.example.dodo.domain.usecase.todo

import com.example.dodo.domain.entity.todo.TodoEntity
import com.example.dodo.domain.repository.todo.TodoRepository
import com.example.dodo.domain.usecase.base.BaseUseCase
import javax.inject.Inject

class DeleteTodoUseCase @Inject constructor(
    private val todoRepository: TodoRepository
) : BaseUseCase<TodoEntity?, Unit>() {
    override suspend fun invoke(data: TodoEntity?) =
        todoRepository.deleteTodo(todo = data)
}