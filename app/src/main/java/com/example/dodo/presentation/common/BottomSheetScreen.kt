package com.example.dodo.presentation.common

sealed class BottomSheetScreen() {
    object TodoAddMapBottomSheet : BottomSheetScreen()
    object TodoAddTimeSelectBottomSheet : BottomSheetScreen()
}
