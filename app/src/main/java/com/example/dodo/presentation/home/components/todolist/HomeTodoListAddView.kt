package com.example.dodo.presentation.home.components.todolist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dodo.R
import com.example.dodo.presentation.home.HomeTodoViewModel
import com.example.dodo.ui.theme.MediumN12
import com.example.dodo.ui.theme.gray0
import com.example.dodo.ui.theme.gray09
import com.example.dodo.util.noRippleClickable
import java.time.LocalDate

@Composable
fun HomeTodoListAddView(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    onClickAdd: (LocalDate) -> Unit
) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(30.dp))
            .background(gray0)
            .noRippleClickable {
                onClickAdd(selectedDate)
            },
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 7.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "추가",
                style = MediumN12,
                color = gray09
            )
            Icon(
                modifier = Modifier.padding(start = 5.dp),
                painter = painterResource(id = R.drawable.ic_todo_add),
                contentDescription = null,
                tint = gray09
            )
        }
    }
}