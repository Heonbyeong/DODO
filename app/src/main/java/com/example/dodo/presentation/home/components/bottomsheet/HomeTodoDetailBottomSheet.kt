package com.example.dodo.presentation.home.components.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.dodo.R
import com.example.dodo.ui.theme.BoldN12
import com.example.dodo.ui.theme.BoldN14
import com.example.dodo.ui.theme.MediumN12
import com.example.dodo.ui.theme.gray0
import com.example.dodo.ui.theme.gray03
import com.example.dodo.ui.theme.gray09
import com.example.dodo.ui.theme.red

@Composable
fun HomeTodoDetailBottomSheet() {
    var isNotify by remember { mutableStateOf(true) }
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.weather_storm)
    )
    val lottieAnimatable = rememberLottieAnimatable()
    
    LaunchedEffect(composition) {
        lottieAnimatable.animate(
            composition = composition,
            clipSpec = LottieClipSpec.Frame(0, 1200),
            initialProgress = 0f
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(gray09)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "당근 거래 하기", // TODO
            style = BoldN14,
            color = gray0
        )
        if (true) { // TODO 시간 설정 한 경우
            Text(
                modifier = Modifier.padding(top = 5.dp),
                text = "오후 2시 30분", // TODO
                style = BoldN12,
                color = gray03
            )
        }
        if (true) { // TODO 장소 일정일 경우
            Text(
                modifier = Modifier.padding(top = 5.dp),
                text = "서울 중구 세종대로 110 서울특별시청",
                style = MediumN12,
                color = gray03
            )
            LottieAnimation(
                modifier = Modifier
                    .size(80.dp)
                    .padding(top = 10.dp),
                composition = composition,
                iterations = LottieConstants.IterateForever,
            )
            Text(
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 40.dp),
                text = "26°C",
                style = BoldN14,
                color = gray0
            )
        }
        if (true) { // TODO 장소 일정일 경우
            HomeTodoDetailBottomSheetItem(
                modifier = Modifier.padding(bottom = 20.dp),
                color = gray0,
                itemType = ItemType.NAVIGATION,
                onClickItem = { /*TODO*/ },
                onCheckedChange = null
            )
        }
        HomeTodoDetailBottomSheetItem(
            modifier = Modifier.padding(vertical = 10.dp),
            color = gray0,
            itemType = ItemType.EDIT,
            onClickItem = { /*TODO*/ },
            onCheckedChange = null,
        )
        HomeTodoDetailBottomSheetItem(
            modifier = Modifier.padding(vertical = 10.dp),
            color = gray0,
            checked = isNotify,
            itemType = ItemType.NOTIFY,
            onClickItem = { /*TODO*/ },
            onCheckedChange = { isNotify = it }
        )
        HomeTodoDetailBottomSheetItem(
            modifier = Modifier.padding(vertical = 10.dp),
            color = red,
            itemType = ItemType.DELETE,
            onClickItem = { /*TODO*/ },
            onCheckedChange = null
        )
    }
}