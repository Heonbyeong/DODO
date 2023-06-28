package com.example.dodo.presentation.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dodo.presentation.setting.components.item.SettingInformationItem
import com.example.dodo.ui.theme.BoldN20
import com.example.dodo.ui.theme.MediumN10
import com.example.dodo.ui.theme.gray0
import com.example.dodo.ui.theme.gray04
import com.example.dodo.ui.theme.gray08

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
) {
    val version = remember { mutableStateOf("1.0.0") } // TODO
    Column(modifier = modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.padding(20.dp),
            text = "설정",
            style = BoldN20,
            color = gray0
        )
        SettingInformationItem(
            modifier = Modifier.fillMaxWidth(),
            text = "화면",
            onItemClick = {} // TODO
        )
        SettingInformationItem(
            modifier = Modifier.fillMaxWidth(),
            text = "알림 설정",
            onItemClick = {} // TODO
        )
        SettingInformationItem(
            modifier = Modifier.fillMaxWidth(),
            text = "버전 정보",
            rightContent = {
                Text(
                    text = version.value,
                    style = MediumN10,
                    color = gray04
                )
            },
        )
        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = gray08
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingScreenPreView() {
    SettingScreen()
}