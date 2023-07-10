package com.example.dodo.domain.usecase.todo

import com.example.dodo.domain.entity.todo.TodoEntity
import com.example.dodo.domain.repository.todo.TodoRepository
import com.example.dodo.domain.usecase.base.BaseUseCase
import javax.inject.Inject

class AddTodoUseCase @Inject constructor(
    private val todoRepository: TodoRepository
) : BaseUseCase<TodoEntity, Unit>() {

    override suspend operator fun invoke(todoEntity: TodoEntity) =
        todoRepository.addTodo(todo = todoEntity)
}