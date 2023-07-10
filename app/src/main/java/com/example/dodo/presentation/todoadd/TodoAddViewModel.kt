package com.example.dodo.presentation.todoadd

import android.location.Geocoder
import androidx.lifecycle.viewModelScope
import com.example.dodo.domain.entity.todo.TodoEntity
import com.example.dodo.domain.entity.todoadd.SearchAddressEntity
import com.example.dodo.domain.param.SearchAddressParam
import com.example.dodo.domain.usecase.todoadd.AddTodoUseCase
import com.example.dodo.domain.usecase.todoadd.FetchTodoListWithDateUseCase
import com.example.dodo.domain.usecase.todoadd.SearchAddressUseCase
import com.example.dodo.presentation.base.BaseViewModel
import com.example.dodo.presentation.common.BottomSheetScreen
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class TodoAddViewModel @Inject constructor(
    private val fetchTodoListWithDateUseCase: FetchTodoListWithDateUseCase,
    private val addTodoUseCase: AddTodoUseCase,
    private val searchAddressUseCase: SearchAddressUseCase,
    private val geocoder: Geocoder
) : BaseViewModel<TodoAddState, TodoAddSideEffect>() {

    override val container = container<TodoAddState, TodoAddSideEffect>(TodoAddState())

    init {
        fetchTodoListWithDate()
    }

    fun fetchTodoListWithDate() = intent {
        if (!state.isLoading) {
            loadingStart()
            viewModelScope.launch {
                val todoList = fetchTodoListWithDateUseCase(state.date)
                reduce {
                    state.copy(todoList = todoList)
                }
                loadingFinish()
            }
        }
    }

    fun addTodo() {
        intent {
            if (!state.isLoading && state.todo.isNotEmpty()) {
                loadingStart()
                val todoEntity = TodoEntity(
                    title = state.todo,
                    location = state.newAddress,
                    date = state.date,
                    time = state.time,
                    lat = state.latitude,
                    lng = state.longitude,
                    isNotify = true,
                    isDone = false
                )
                viewModelScope.launch {
                    addTodoUseCase(data = todoEntity)
                    clearTodoAddState(todoEntity = todoEntity)
                    loadingFinish()
                }
            }
        }
    }

    fun searchAddress() {
        intent {
            if (!state.isLoading) {
                loadingStart()
                val searchAddressParam = SearchAddressParam(keyword = state.addressText)

                runCatching {
                    searchAddressUseCase(data = searchAddressParam)
                }.onSuccess {
                    setAddress(it.results.juso)
                }.onFailure {
                    it
                }.also {
                    loadingFinish()
                }
            }
        }
    }

    fun geocoding() = intent {
        viewModelScope.launch {
            val address = geocoder.getFromLocationName(state.newAddress, 2)
            address?.get(0)?.let {
                reduce {
                    state.copy(
                        latitude = it.latitude,
                        longitude = it.longitude
                    )
                }
            }
        }
    }

    fun reverseGeocoding(latLng: LatLng) {
        intent {
            reduce {
                state.copy(
                    longitude = latLng.longitude,
                    latitude = latLng.latitude
                )
            }
        }
        viewModelScope.launch {
            val address = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 2)
            address?.get(0)?.let {
                intent {
                    reduce {
                        state.copy(
                            newAddress = it.getAddressLine(0).removePrefix("대한민국"),
                            oldAddress = ""
                        )
                    }
                }
            }
        }
    }

    fun onChangeDate(date: LocalDate) = intent {
        reduce {
            state.copy(date = date)
        }
    }

    fun onChangeTodoText(text: String) = intent {
        reduce {
            state.copy(todo = text)
        }
    }

    fun onChangeAddressText(text: String) = intent {
        reduce {
            state.copy(addressText = text)
        }
    }

    fun onChangeTime(time: LocalTime) = intent {
        reduce {
            state.copy(time = time)
        }
    }

    fun onClickMap() = intent {
        reduce {
            state.copy(currentSheet = BottomSheetScreen.TodoAddMapBottomSheet)
        }
    }

    fun onClickTimeSelector() = intent {
        reduce {
            state.copy(currentSheet = BottomSheetScreen.TodoAddTimeSelectBottomSheet)
        }
    }

    fun onClickAddressItem(juso: SearchAddressEntity.JusoEntity) = intent {
        geocoding()
        reduce {
            state.copy(
                newAddress = juso.roadAddr,
                oldAddress = juso.jibunAddr
            )
        }
    }

    fun onClickSetDestination() = intent {
        reduce {
            state.copy(hasDestination = true)
        }
    }

    fun onClickSetTime() = intent {
        reduce {
            state.copy(hasTime = true)
        }
    }

    private fun setAddress(jusoList: List<SearchAddressEntity.JusoEntity>) = intent {
        reduce {
            state.copy(jusoList = jusoList)
        }
    }

    private fun clearTodoAddState(todoEntity: TodoEntity) = intent {
        reduce {
            val updateList = state.todoList + todoEntity
            state.copy(
                todoList = updateList,
                todo = "",
                longitude = 0.0,
                latitude = 0.0,
                addressText = "",
                newAddress = "",
                oldAddress = "",
                jusoList = emptyList(),
                time = LocalTime.now(),
                hasDestination = false,
                hasTime = false
            )
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