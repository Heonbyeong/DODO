package com.example.dodo.presentation.home.components.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.dodo.R
import com.example.dodo.presentation.home.HomeTodoViewModel
import com.example.dodo.ui.theme.BoldN12
import com.example.dodo.ui.theme.BoldN14
import com.example.dodo.ui.theme.MediumN12
import com.example.dodo.ui.theme.gray0
import com.example.dodo.ui.theme.gray03
import com.example.dodo.ui.theme.gray09
import com.example.dodo.ui.theme.red
import com.example.dodo.util.timeFormat
import org.orbitmvi.orbit.compose.collectAsState
import java.util.Locale

@Composable
fun HomeTodoDetailBottomSheet(
    modifier: Modifier = Modifier,
    viewModel: HomeTodoViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState().value
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
        modifier = modifier
            .fillMaxWidth()
            .background(gray09)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = state.todoDetail?.title.orEmpty(),
            style = BoldN14,
            color = gray0
        )
        val hasTime = state.todoDetail?.time != null
        if (hasTime) {
            Text(
                modifier = Modifier.padding(top = 5.dp),
                text = state.todoDetail?.time?.timeFormat("a h:mm", Locale.KOREAN).orEmpty(),
                style = BoldN12,
                color = gray03
            )
        }
        val hasDestination = state.todoDetail?.location.orEmpty().isNotEmpty()
        if (hasDestination) {
            Text(
                modifier = Modifier.padding(top = 5.dp),
                text = state.todoDetail?.location.orEmpty(),
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
                    .padding(top = 5.dp),
                text = "26°C",
                style = BoldN14,
                color = gray0
            )
        }
        if (false) { // TODO 추후 추가 될 기능
            Spacer(modifier = Modifier.height(40.dp))
            HomeTodoDetailBottomSheetItem(
                modifier = Modifier.padding(bottom = 20.dp),
                color = gray0,
                itemType = ItemType.NAVIGATION,
                onClickItem = { /*TODO*/ },
                onCheckedChange = null
            )
        } else {
            Spacer(modifier = Modifier.height(40.dp))
        }
        HomeTodoDetailBottomSheetItem(
            modifier = Modifier.padding(vertical = 10.dp),
            color = gray0,
            itemType = ItemType.EDIT,
            onClickItem = {
                viewModel.onClickAdd(
                    date = state.selectedDate,
                    isEdit = true,
                    id = state.todoDetail?.id ?: 0
                )
            },
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