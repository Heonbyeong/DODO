package com.example.dodo.presentation.todoadd.components.bottomsheet

import android.graphics.PointF
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dodo.R
import com.example.dodo.presentation.common.BottomSheetButton
import com.example.dodo.presentation.todoadd.TodoAddViewModel
import com.example.dodo.ui.theme.BoldN12
import com.example.dodo.ui.theme.RegularN12
import com.example.dodo.ui.theme.gray0
import com.example.dodo.ui.theme.gray07
import com.example.dodo.ui.theme.gray09
import com.example.dodo.util.noRippleClickable
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate.scrollBy
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.compose.rememberFusedLocationSource
import com.naver.maps.map.overlay.OverlayImage
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(
    ExperimentalNaverMapApi::class, ExperimentalComposeUiApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun TodoAddMapBottomSheet(
    modifier: Modifier = Modifier,
    viewModel: TodoAddViewModel = hiltViewModel(),
) {
    val state = viewModel.collectAsState().value
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val cameraPositionState = rememberCameraPositionState()
    val locationSource = rememberFusedLocationSource()
    var isMoving by remember { mutableStateOf(false) }
    var mapProperties by remember {
        mutableStateOf(MapProperties(locationTrackingMode = LocationTrackingMode.NoFollow))
    }
    var mapUiSettings by remember {
        mutableStateOf(MapUiSettings(isLocationButtonEnabled = true))
    }

    val closeSheet: () -> Unit = {
        coroutineScope.launch { sheetState.hide() }
    }

    val openSheet: () -> Unit = {
        coroutineScope.launch { sheetState.show() }
    }

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

    LaunchedEffect(isMoving) {
        if (!isMoving) {
            viewModel.reverseGeocoding(cameraPositionState.position.target)
        }
    }

    ModalBottomSheetLayout(
        sheetContent = {
            TodoAddAddressBottomSheet(
                modifier = Modifier.padding(20.dp),
                closeSheet = closeSheet
            )
        },
        sheetState = sheetState,
        sheetBackgroundColor = gray09,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
    ) {
        Column(modifier = modifier) {
            Box(modifier = Modifier.weight(1f)) {
                NaverMap(
                    modifier = Modifier
                        .fillMaxSize()
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDragEnd = {
                                    isMoving = false
                                }
                            ) { _, dragAmount ->
                                if (!isMoving) isMoving = true
                                cameraPositionState.move(
                                    scrollBy(
                                        PointF(
                                            dragAmount.x,
                                            dragAmount.y
                                        )
                                    )
                                )
                            }
                        },
                    properties = mapProperties,
                    uiSettings = mapUiSettings,
                    cameraPositionState = cameraPositionState,
                    locationSource = locationSource
                ) {
                    Marker(
                        state = MarkerState(
                            position = LatLng(
                                cameraPositionState.position.target.latitude,
                                cameraPositionState.position.target.longitude
                            )
                        ),
                        icon = OverlayImage.fromResource(R.drawable.ic_bottomsheet_current_marker)
                    )
                }
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(gray09),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp),
                    text = "[도로명] ${state.newAddress}",
                    style = BoldN12,
                    color = gray0
                )
                if (state.hasOldAddress) {
                    Text(
                        modifier = Modifier.padding(top = 5.dp),
                        text = "[지번] ${state.oldAddress}", // TODO
                        style = RegularN12,
                        color = gray0
                    )
                }
                BottomSheetButton(
                    modifier = Modifier.padding(20.dp),
                    text = "여기를 도착지로 설정할게요",
                    enabled = !isMoving,
                    onClick = { /* TODO */ },
                )
            }
        }
    }
}