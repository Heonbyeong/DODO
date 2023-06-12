package com.example.dodo.presentation.home.components.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dodo.ui.theme.BoldN14
import com.example.dodo.ui.theme.MediumN10
import com.example.dodo.ui.theme.gray0
import com.example.dodo.ui.theme.gray03
import com.example.dodo.ui.theme.gray09
import com.example.dodo.ui.theme.red

@Composable
fun HomeTodoDetailBottomSheet() {
    var isNotify by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(gray09)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "당근 거래 하기", // TODO
            style = BoldN14,
            color = gray0
        )
        Text(
            modifier = Modifier.padding(top = 5.dp, bottom = 40.dp),
            text = "오후 2시 30분", // TODO
            style = MediumN10,
            color = gray03
        )
        HomeTodoDetailBottomSheetItem(
            modifier = Modifier.padding(bottom = 10.dp),
            color = gray0,
            itemType = ItemType.EDIT,
            onClickItem = { /*TODO*/ },
            onCheckedChange = null,
        )
        HomeTodoDetailBottomSheetItem(
            modifier = Modifier.padding(vertical = 10.dp),
            color = gray0,
            checked = isNotify,
            itemType = ItemType.NOTIFY,
            onClickItem = { /*TODO*/ },
            onCheckedChange = { isNotify = it }
        )
        HomeTodoDetailBottomSheetItem(
            modifier = Modifier.padding(vertical = 10.dp),
            color = red,
            itemType = ItemType.DELETE,
            onClickItem = { /*TODO*/ },
            onCheckedChange = null
        )
    }
}