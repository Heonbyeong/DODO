package com.example.dodo.presentation.home.components.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dodo.presentation.home.HomeTodoViewModel
import com.example.dodo.ui.theme.BlackN12
import com.example.dodo.ui.theme.BoldN12
import com.example.dodo.ui.theme.gray0
import com.example.dodo.ui.theme.gray07
import com.example.dodo.ui.theme.gray08
import com.example.dodo.ui.theme.gray09
import com.example.dodo.util.conditional
import com.example.dodo.util.dateFormat
import com.example.dodo.util.noRippleClickable
import org.orbitmvi.orbit.compose.collectAsState
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalCalendar(
    modifier: Modifier = Modifier,
    config: HorizontalCalendarConfig = HorizontalCalendarConfig(),
    viewModel: HomeTodoViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState().value
    //page는 0부터 시작하기 때문에 getMonthValue - 1을 해줘야 함
    val initialPage = (state.selectedDate.year - config.yearRange.first) * 12 + state.selectedDate.monthValue - 1
    var currentMonth by remember { mutableStateOf(state.selectedDate) }
    var currentPage by remember { mutableStateOf(initialPage) }
    val pagerState = rememberPagerState(initialPage = initialPage)

    LaunchedEffect(pagerState.currentPage) {
        val addMonth = (pagerState.currentPage - currentPage).toLong()
        currentMonth = currentMonth.plusMonths(addMonth)
        currentPage = pagerState.currentPage
    }

    Column(modifier = modifier) {
        val headerText = currentMonth.dateFormat("yyyy년 M월")
        val pageCount = (config.yearRange.last - config.yearRange.first) * 12
        CalendarHeader(
            modifier = Modifier.padding(20.dp),
            text = headerText
        )
        HorizontalPager(
            pageCount = pageCount,
            state = pagerState
        ) { page ->
            val date = LocalDate.of(
                config.yearRange.first + page / 12,
                page % 12 + 1,
                1
            )
            if (page in pagerState.currentPage - 1..pagerState.currentPage + 1) { // 페이징 성능 개선을 위한 조건문
                CalendarMonthItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    currentDate = date,
                    selectedDate = state.selectedDate,
                    onSelectedDate = viewModel::onChangeDate
                )
            }
        }
    }
}

@Composable
fun CalendarHeader(
    modifier: Modifier = Modifier,
    text: String,
) {
    Box(modifier = modifier) {
        Text(
            text = text,
            style = BlackN12
        )
    }
}

@Composable
fun CalendarMonthItem(
    modifier: Modifier = Modifier,
    currentDate: LocalDate,
    selectedDate: LocalDate,
    onSelectedDate: (LocalDate) -> Unit
) {
    val lastDay by remember { mutableStateOf(currentDate.lengthOfMonth()) }
    val firstDayOfWeek by remember { mutableStateOf(currentDate.dayOfWeek.value) }
    val days by remember { mutableStateOf(IntRange(1, lastDay).toList()) }

    Column(modifier = modifier) {
        DayOfWeek()
        LazyVerticalGrid(
            modifier = Modifier.height(260.dp),
            columns = GridCells.Fixed(7)
        ) {
            for (i in 1 until firstDayOfWeek) { // 처음 날짜가 시작하는 요일 전까지 빈 박스 생성
                item {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .padding(top = 10.dp)
                    )
                }
            }
            items(days) { day ->
                val date = currentDate.withDayOfMonth(day)
                val isSelected = remember(selectedDate) {
                    selectedDate.compareTo(date) == 0
                }
                CalendarDay(
                    modifier = Modifier.padding(top = 10.dp),
                    date = date,
                    isToday = date == LocalDate.now(),
                    isSelected = isSelected,
                    onSelectedDate = onSelectedDate
                )
            }
        }
    }
}

@Composable
fun CalendarDay(
    modifier: Modifier = Modifier,
    date: LocalDate,
    isToday: Boolean,
    isSelected: Boolean,
    hasEvent: Boolean = false,
    onSelectedDate: (LocalDate) -> Unit
) {
    Column(
        modifier = modifier
            .wrapContentSize()
            .size(30.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .conditional(isToday) {
                background(gray07)
            }
            .conditional(isSelected) {
                background(gray0)
            }
            .conditional(!isToday && !isSelected) {
                background(gray08)
            }
            .noRippleClickable { onSelectedDate(date) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        val textColor = if (isSelected) gray09 else gray0
        Text(
            modifier = Modifier,
            textAlign = TextAlign.Center,
            text = date.dayOfMonth.toString(),
            style = BoldN12,
            color = textColor
        )
        if (hasEvent) {
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .clip(shape = RoundedCornerShape(4.dp))
                    .conditional(isSelected) {
                        background(gray09)
                    }
                    .conditional(!isSelected) {
                        background(gray0)
                    }
            )
        }
    }
}

@Composable
fun DayOfWeek(
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        DayOfWeek.values().forEach { dayOfWeek ->
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN),
                style = BlackN12,
                textAlign = TextAlign.Center
            )
        }
    }
}