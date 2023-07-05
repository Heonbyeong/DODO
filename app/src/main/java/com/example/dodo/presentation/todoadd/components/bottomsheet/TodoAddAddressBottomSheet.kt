package com.example.dodo.presentation.todoadd.components.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dodo.R
import com.example.dodo.presentation.todoadd.TodoAddViewModel
import com.example.dodo.ui.theme.BoldN10
import com.example.dodo.ui.theme.RegularN10
import com.example.dodo.ui.theme.RegularN12
import com.example.dodo.ui.theme.gray0
import com.example.dodo.ui.theme.gray04
import com.example.dodo.ui.theme.gray07
import com.example.dodo.ui.theme.gray08
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun TodoAddAddressBottomSheet(
    modifier: Modifier = Modifier,
    viewModel: TodoAddViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState().value
    val list = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8) // TODO

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(gray08),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.padding(13.dp),
                painter = painterResource(id = R.drawable.ic_bottomsheet_search),
                contentDescription = null,
                tint = gray07
            )
            val addressText = state.addressText
            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = addressText,
                onValueChange = { viewModel.changeAddressText(it) },
                singleLine = true,
                textStyle = RegularN12,
                decorationBox = { innerTextField ->
                    if (addressText.isEmpty()) {
                        Text(
                            text = "장소·건물·주소를 검색해주세요",
                            style = RegularN12,
                            color = gray04
                        )
                    }
                    innerTextField()
                }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            itemsIndexed(items = list) { index, item ->
                LocationItem(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
fun LocationItem(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_bottomsheet_location),
            contentDescription = null
        )
        Column(modifier = Modifier.padding(top = 15.dp, start = 20.dp, bottom = 15.dp)) {
            Text(
                text = "서울 중구 세종대로 110 서울특별시청", // TODO
                style = BoldN10,
                color = gray0,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                modifier = Modifier.padding(top = 5.dp),
                text = "태평로1가 31", // TODO
                style = RegularN10,
                color = gray0,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}