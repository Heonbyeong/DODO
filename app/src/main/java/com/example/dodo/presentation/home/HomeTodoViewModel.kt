package com.example.dodo.presentation.home

import androidx.lifecycle.viewModelScope
import com.example.dodo.domain.entity.todo.TodoEntity
import com.example.dodo.domain.usecase.todoadd.FetchTodoListWithDateUseCase
import com.example.dodo.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeTodoViewModel @Inject constructor(
    private val fetchTodoListWithDateUseCase: FetchTodoListWithDateUseCase
) : BaseViewModel<HomeTodoState, HomeTodoSideEffect>() {

    override val container = container<HomeTodoState, HomeTodoSideEffect>(HomeTodoState())

    init {
        fetchTodoListWithDate()
    }

    fun fetchTodoListWithDate() = intent {
        if (!state.isLoading) {
            loadingStart()
            viewModelScope.launch {
                val todoList = fetchTodoListWithDateUseCase(state.selectedDate)
                reduce {
                    state.copy(todoList = todoList)
                }
                loadingFinish()
            }
        }
    }

    fun onChangeDate(date: LocalDate) = intent {
        reduce {
            state.copy(selectedDate = date)
        }
    }

    fun onClickTodoItem(todo: TodoEntity) = intent {
        reduce {
            state.copy(todoDetail = todo)
        }
    }

    fun onClickAdd(date: LocalDate, isEdit: Boolean = false, id: Int = 0) = intent {
        postSideEffect(
            HomeTodoSideEffect.MoveToAdd(
                date = date.toString(),
                isEdit = isEdit,
                id = id
            )
        )
    }

    private fun loadingStart() = intent {
        reduce {
            state.copy(isLoading = true)
        }
    }

    private fun loadingFinish() = intent {
        reduce {
            state.copy(isLoading = false)
        }
    }
}