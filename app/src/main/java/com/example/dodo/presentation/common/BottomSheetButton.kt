package com.example.dodo.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.dodo.ui.theme.BoldN14
import com.example.dodo.ui.theme.gray0
import com.example.dodo.ui.theme.gray06
import com.example.dodo.ui.theme.gray09
import com.example.dodo.util.noRippleClickable

@Composable
fun BottomSheetButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = false,
    onClick: () -> Unit
) {
    if (enabled) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(40.dp))
                .background(gray0)
                .noRippleClickable { onClick() },
            Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(vertical = 15.dp),
                text = text,
                style = BoldN14,
                color = gray09
            )
        }
    } else {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .border(1.dp, gray06, RoundedCornerShape(40.dp)),
            Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(vertical = 15.dp),
                text = text,
                style = BoldN14,
                color = gray06
            )
        }
    }
}