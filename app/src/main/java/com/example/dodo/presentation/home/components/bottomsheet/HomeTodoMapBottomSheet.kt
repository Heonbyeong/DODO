package com.example.dodo.presentation.home.components.bottomsheet

import android.graphics.PointF
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dodo.R
import com.example.dodo.ui.theme.RegularN12
import com.example.dodo.ui.theme.gray07
import com.example.dodo.ui.theme.gray09
import com.example.dodo.util.noRippleClickable
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalNaverMapApi::class, ExperimentalComposeUiApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun HomeTodoMapBottomSheet(
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val cameraPositionState = rememberCameraPositionState()

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
    }

    LaunchedEffect(Unit) {
        snapshotFlow { sheetState.currentValue }.collect {
            if (it == ModalBottomSheetValue.Hidden) {
                keyboardController?.hide()
                focusManager.clearFocus()
            }
        }
    }

    ModalBottomSheetLayout(
        sheetContent = { HomeTodoLocationBottomSheet(modifier = Modifier.padding(20.dp)) },
        sheetState = sheetState,
        sheetBackgroundColor = gray09,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
    ) {
        Box(modifier = modifier) {
            NaverMap(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectDragGestures { _, dragAmount ->
                            cameraPositionState.move(CameraUpdate.scrollBy(PointF(dragAmount.x, dragAmount.y)))
                        }
                    },
                cameraPositionState = cameraPositionState
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .shadow(elevation = 20.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(gray09)
                    .align(Alignment.TopCenter)
                    .noRippleClickable {
                        coroutineScope.launch {
                            sheetState.show()
                        }
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.padding(13.dp),
                    painter = painterResource(id = R.drawable.ic_bottomsheet_search),
                    contentDescription = null,
                    tint = gray07
                )
                Text(
                    text = "장소·건물·주소를 검색해주세요",
                    style = RegularN12,
                    color = gray07
                )
            }
        }
    }
}