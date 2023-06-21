package com.example.dodo.presentation.base

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost

abstract class BaseViewModel<S: State, E: SideEffect> : ContainerHost<S, E>, ViewModel() {

    abstract fun sendEvent(event: E)
}