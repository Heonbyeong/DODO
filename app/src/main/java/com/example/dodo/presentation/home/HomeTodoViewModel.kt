package com.example.dodo.presentation.home

import com.example.dodo.domain.features.home.todo.repository.TodoRepository
import com.example.dodo.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeTodoViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : BaseViewModel<HomeTodoState, HomeTodoSideEffect>() {

    override val container = container<HomeTodoState, HomeTodoSideEffect>(HomeTodoState())

    fun onChangeDate(date: LocalDate) = intent {
        reduce {
            state.copy(selectedDate = date)
        }
    }
}