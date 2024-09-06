package com.thezayin.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.thezayin.entities.PreviewMenu

@Composable
internal fun PreviewMenu(modifier: Modifier, items: List<PreviewMenu>, callback: (Int) -> Unit) {
    Row(
        modifier = modifier
            .padding(horizontal = 25.dp, vertical = 40.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = colorResource(id = com.thezayin.drawable.R.color.transparent)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            items(items.size) { index ->
                MenuItem(item = items[index], callback = callback)
            }
        }
    }
}