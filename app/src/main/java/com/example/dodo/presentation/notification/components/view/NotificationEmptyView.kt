package com.example.dodo.presentation.notification.components.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.dodo.ui.theme.RegularN12
import com.example.dodo.ui.theme.gray03

@Composable
fun NotificationEmptyView(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "알림이 없어요",
            style = RegularN12,
            color = gray03,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationEmptyPreView() {
    NotificationEmptyView(modifier = Modifier.fillMaxSize())
}