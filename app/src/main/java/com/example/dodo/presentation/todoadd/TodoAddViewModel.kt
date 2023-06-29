package com.example.dodo.presentation.todoadd

import com.example.dodo.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class TodoAddViewModel @Inject constructor() : BaseViewModel<TodoAddState, TodoAddSideEffect>() {
    override val container = container<TodoAddState, TodoAddSideEffect>(TodoAddState())
}