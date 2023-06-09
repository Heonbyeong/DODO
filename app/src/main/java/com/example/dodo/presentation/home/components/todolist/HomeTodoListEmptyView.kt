package com.example.dodo.presentation.home.components.todolist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dodo.ui.theme.BoldN12
import com.example.dodo.ui.theme.RegularN12
import com.example.dodo.ui.theme.gray0
import com.example.dodo.ui.theme.gray03
import com.example.dodo.ui.theme.gray09

@Composable
fun HomeTodoListEmptyView(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = "아직 할 일이 없어요",
            style = RegularN12,
            color = gray03
        )
        Box(
            modifier = Modifier
                .background(
                    color = gray0,
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 9.dp),
                text = "할 일 추가하기",
                style = BoldN12,
                color = gray09
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeTodoListEmptyPreView() {
    HomeTodoListEmptyView()
}