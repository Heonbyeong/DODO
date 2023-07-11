package com.example.dodo.presentation.home

import androidx.lifecycle.viewModelScope
import com.example.dodo.domain.usecase.todoadd.FetchTodoListWithDateUseCase
import com.example.dodo.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
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