package com.example.dodo.presentation.todoadd.components.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.dodo.ui.theme.BoldN12
import com.example.dodo.ui.theme.MediumN10
import com.example.dodo.ui.theme.MediumN12
import com.example.dodo.ui.theme.RegularN10
import com.example.dodo.ui.theme.gray0
import com.example.dodo.ui.theme.gray08
import com.example.dodo.ui.theme.gray09

@Composable
fun TodoAddItem(
    modifier: Modifier = Modifier,
    isMine: Boolean = true,
) {
    val hasLocation = true // TODO
    Box(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .width(200.dp)
                .background(gray09, RoundedCornerShape(10.dp))
                .align(Alignment.CenterEnd)
        ) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(10.dp)
            ) {
                Text(
                    text = "할 일을 등록했어요.",
                    style = BoldN12,
                    color = gray0
                )
                if (hasLocation) {
                    Text(
                        text = "장소 일정은 두두가 관리할게요!",
                        style = RegularN10,
                        color = gray0
                    )
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    thickness = 1.dp,
                    color = gray08
                )
                Text(
                    text = "당근 거래하기 (아이폰 14 Pro)", // TODO
                    style = MediumN12,
                    color = gray0
                )
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = "시간 : 오후 8시 44분", // TODO
                    style = MediumN10,
                    color = gray0
                )
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = "장소 : 서울 중구 세종대로 110 서울특별시청", // TODO
                    style = MediumN10,
                    color = gray0,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}