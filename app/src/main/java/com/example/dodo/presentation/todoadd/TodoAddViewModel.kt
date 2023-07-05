package com.example.dodo.presentation.todoadd

import android.location.Geocoder
import androidx.lifecycle.viewModelScope
import com.example.dodo.presentation.base.BaseViewModel
import com.example.dodo.presentation.common.BottomSheetScreen
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class TodoAddViewModel @Inject constructor(
    private val geocoder: Geocoder
) : BaseViewModel<TodoAddState, TodoAddSideEffect>() {

    override val container = container<TodoAddState, TodoAddSideEffect>(TodoAddState())

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
                            newAddress = it.getAddressLine(0).removePrefix("대한민국")
                        )
                    }
                }
            }
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
}