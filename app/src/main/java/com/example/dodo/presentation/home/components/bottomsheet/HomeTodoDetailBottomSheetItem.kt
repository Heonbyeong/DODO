package com.example.dodo.presentation.home.components.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dodo.R
import com.example.dodo.ui.theme.MediumN14
import com.example.dodo.ui.theme.gray0
import com.example.dodo.ui.theme.gray08
import com.example.dodo.ui.theme.gray09
import com.example.dodo.util.noRippleClickable

enum class ItemType(val icon: Int, val text: String) {
    EDIT(icon = R.drawable.ic_bottomsheet_edit, text = "수정하기"),
    NOTIFY(icon = R.drawable.ic_bottom_notify, text = "알림설정"),
    DELETE(icon = R.drawable.ic_bottomsheet_delete, text = "삭제하기"),
    NAVIGATION(icon = R.drawable.ic_bottomsheet_navigation, text = "지도에서 길찾기")
}

@Composable
fun HomeTodoDetailBottomSheetItem(
    modifier: Modifier = Modifier,
    color: Color,
    itemType: ItemType,
    checked: Boolean = true,
    onClickItem: (() -> Unit)?,
    onCheckedChange: ((Boolean) -> Unit)?
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .noRippleClickable {
                onClickItem?.let { it() }
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = color),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = itemType.icon),
                contentDescription = null,
                tint = gray09
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 10.dp),
            text = itemType.text,
            style = MediumN14,
            color = color
        )
        if (itemType == ItemType.NOTIFY) {
            Switch(
                checked = checked,
                onCheckedChange = { onCheckedChange?.let { function -> run { function(it) } } },
                colors = SwitchDefaults.colors(
                    checkedTrackColor = gray0,
                    uncheckedBorderColor = gray08,
                    uncheckedTrackColor = gray08,
                    uncheckedThumbColor = gray09
                )
            )
        }
    }
}