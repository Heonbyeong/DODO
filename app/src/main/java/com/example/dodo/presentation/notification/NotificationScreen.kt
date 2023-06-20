package com.example.dodo.presentation.notification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dodo.presentation.notification.components.item.NotificationItem
import com.example.dodo.presentation.notification.components.item.NotifyType
import com.example.dodo.presentation.notification.components.view.NotificationEmptyView
import com.example.dodo.ui.theme.BoldN20
import com.example.dodo.ui.theme.RegularN12
import com.example.dodo.ui.theme.gray0
import com.example.dodo.ui.theme.gray03

@Composable
fun NotificationScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.padding(20.dp),
            text = "알림",
            style = BoldN20,
            color = gray0
        )
        if (true) { // TODO 알림이 있다면
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val todoList = listOf(1, 2, 3, 4, 5, 6, 7) // TODO 테스트 용
                itemsIndexed(items = todoList) { index, item ->
                    NotificationItem(
                        modifier = Modifier.fillMaxWidth(),
                        notifyType = NotifyType.SUCCESS,
                        isEnd = index == todoList.size - 1
                    )
                }
                item {
                    Text(
                        modifier = Modifier.padding(vertical = 40.dp),
                        text = "알림은 15일 동안만 보관돼요.",
                        style = RegularN12,
                        color = gray03
                    )
                }
            }
        } else {
            NotificationEmptyView(modifier = Modifier.fillMaxSize())
        }
    }
}