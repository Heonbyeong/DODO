package com.example.dodo.presentation.base

sealed class BottomSheetScreen() {
    object TodoAddMapBottomSheet : BottomSheetScreen()
    object TodoAddTimeSelectBottomSheet : BottomSheetScreen()
}
