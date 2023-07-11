package com.example.dodo.presentation.home.components.todolist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.dodo.R
import com.example.dodo.domain.entity.todo.TodoEntity
import com.example.dodo.ui.theme.BoldN12
import com.example.dodo.ui.theme.MediumN10
import com.example.dodo.ui.theme.gray0
import com.example.dodo.ui.theme.gray07
import com.example.dodo.util.noRippleClickable
import com.example.dodo.util.timeFormat
import java.util.Locale

@Composable
fun HomeTodoListItem(
    modifier: Modifier = Modifier,
    onClickTodo: () -> Unit,
    todo: TodoEntity
) {
    var isChecked by remember { mutableStateOf(false) }
    Row(
        modifier = modifier.height(60.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HomeTodoListCheckBox(
            modifier = Modifier.padding(horizontal = 20.dp),
            onCheckedChange = { checked ->
                isChecked = !checked
            }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .noRippleClickable { onClickTodo() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = todo.title,
                    style = BoldN12,
                    color = gray0,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                val hasTime = todo.time != null
                if (hasTime) {
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = todo.time?.timeFormat("a h:mm", Locale.KOREAN).orEmpty(),
                        style = MediumN10,
                        color = gray0,
                        maxLines = 1
                    )
                }
            }
            Icon(
                modifier = Modifier.padding(horizontal = 20.dp),
                painter = painterResource(id = R.drawable.ic_todo_more),
                contentDescription = null,
                tint = gray07
            )
        }
    }
}