package com.example.dodo.presentation.todoadd

import android.location.Geocoder
import androidx.lifecycle.viewModelScope
import com.example.dodo.domain.entity.todoadd.SearchAddressEntity
import com.example.dodo.domain.usecase.todoadd.SearchAddressUseCase
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
    private val searchAddressUseCase: SearchAddressUseCase,
    private val geocoder: Geocoder
) : BaseViewModel<TodoAddState, TodoAddSideEffect>() {

    override val container = container<TodoAddState, TodoAddSideEffect>(TodoAddState())

    fun searchAddress() {
        intent {
            if (!state.isLoading) {
                loadingStart()

                runCatching {
                    searchAddressUseCase.invoke(keyword = state.addressText)
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

    fun onChangeAddressText(text: String) = intent {
        reduce {
            state.copy(addressText = text)
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

    private fun setAddress(jusoList: List<SearchAddressEntity.JusoEntity>) = intent {
        reduce {
            state.copy(jusoList = jusoList)
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