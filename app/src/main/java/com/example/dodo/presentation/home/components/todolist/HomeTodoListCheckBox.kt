package com.example.dodo.presentation.home.components.todolist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dodo.R
import com.example.dodo.ui.theme.gray0
import com.example.dodo.ui.theme.gray08
import com.example.dodo.ui.theme.gray09
import com.example.dodo.util.noRippleClickable

@Composable
fun HomeTodoListCheckBox(
    modifier: Modifier = Modifier,
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit
) {
    var isChecked by remember { mutableStateOf(checked) }
    if (isChecked) {
        Box(
            modifier = modifier
                .size(20.dp)
                .clip(shape = RoundedCornerShape(5.dp))
                .background(color = gray0)
                .noRippleClickable {
                    onCheckedChange(checked)
                    isChecked = !isChecked
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_todo_check),
                contentDescription = null,
                tint = gray09
            )
        }
    } else {
        Box(
            modifier = modifier
                .size(20.dp)
                .clip(shape = RoundedCornerShape(5.dp))
                .background(color = gray08)
                .clickable {
                    onCheckedChange(checked)
                    isChecked = !isChecked
                }
        )
    }
}