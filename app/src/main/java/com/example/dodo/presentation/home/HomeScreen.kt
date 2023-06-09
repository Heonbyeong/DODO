package com.example.dodo.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dodo.presentation.home.components.calendar.HorizontalCalendar
import com.example.dodo.presentation.home.components.todolist.HomeTodoListEmptyView
import com.example.dodo.presentation.home.components.todolist.HomeTodoListItem
import com.example.dodo.ui.theme.RegularC32
import java.time.format.DateTimeFormatter

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier.padding(20.dp),
            text = "dodo",
            style = RegularC32
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                var selectDate by remember { mutableStateOf("") } // TODO 테스트 용
                HorizontalCalendar(
                    onSelectedDate = { date ->
                        selectDate = date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
                    }
                )
            }
            val todoList = listOf<Int>(1, 2, 3, 4, 5) // TODO 테스트 용
            if (todoList.isNotEmpty()) {
                itemsIndexed(items = todoList) { index, data ->
                    HomeTodoListItem(modifier = Modifier.fillMaxWidth())
                }
            } else {
                item {
                    HomeTodoListEmptyView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 100.dp)
                    )
                }
            }
        }
    }
}