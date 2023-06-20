package com.example.dodo.presentation.notification.components.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dodo.R
import com.example.dodo.ui.theme.BoldN14
import com.example.dodo.ui.theme.RegularN10
import com.example.dodo.ui.theme.gray0
import com.example.dodo.ui.theme.gray05
import com.example.dodo.ui.theme.gray08
import com.example.dodo.ui.theme.yellow

enum class NotifyType(val icon: Int, val title: String, val description: String, val tint: Color) {
    SUCCESS(
        icon = R.drawable.ic_success,
        title = "일정 완료",
        description = "장소에 도착하여 두두가 일정을 완료됨으로 바꿨어요!",
        tint = gray0
    ),
    WARNING(
        icon = R.drawable.ic_warning,
        title = "일정 누락",
        description = "장소에 도착하지 않으셨나요? 일정이 완료되지 않았어요 :(",
        tint = yellow
    )
}

@Composable
fun NotificationItem(
    modifier: Modifier = Modifier,
    notifyType: NotifyType,
    isEnd: Boolean = false
) {
    Column(modifier = modifier) {
        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = gray08
        )
        Row(
            modifier = Modifier.wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.padding(20.dp),
                painter = painterResource(id = notifyType.icon),
                contentDescription = null,
                tint = notifyType.tint
            )
            Column(modifier = Modifier.padding(vertical = 20.dp)) {
                Text(
                    text = notifyType.title,
                    style = BoldN14,
                    color = gray0
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = notifyType.description,
                    style = RegularN10,
                    color = gray0
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "[두두 만나기] · 6월 5일", // TODO
                    style = RegularN10,
                    color = gray05
                )
            }
        }
        if (isEnd) {
            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = gray08
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationItemPreView() {
    NotificationItem(
        modifier = Modifier.fillMaxWidth(),
        notifyType = NotifyType.SUCCESS
    )
}