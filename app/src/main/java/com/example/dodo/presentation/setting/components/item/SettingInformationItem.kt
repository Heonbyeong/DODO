package com.example.dodo.presentation.setting.components.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dodo.R
import com.example.dodo.ui.theme.MediumN12
import com.example.dodo.ui.theme.gray0
import com.example.dodo.ui.theme.gray07
import com.example.dodo.ui.theme.gray08
import com.example.dodo.util.noRippleClickable

@Composable
fun SettingInformationItem(
    modifier: Modifier = Modifier,
    text: String,
    rightContent: @Composable (() -> Unit)? = null,
    onItemClick: () -> Unit = {}
) {
    Column(modifier = modifier.noRippleClickable { onItemClick() }) {
        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = gray08
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                style = MediumN12,
                color = gray0
            )
            Box {
                if (rightContent != null) {
                    rightContent()
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_setting_right_arrow),
                        contentDescription = null,
                        tint = gray07
                    )
                }
            }
        }
    }
}