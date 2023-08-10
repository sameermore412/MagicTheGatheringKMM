package com.more.mtg.sharedui.widgets

import androidx.compose.foundation.HorizontalScrollbar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
actual fun <T> HorizontalScrollingRow(itemList: List<T>,
                                      contentPadding: Dp,
                                      paddingBetweenChildren: Dp,
                                      itemContent: @Composable (item: T) -> Unit) {
    val state = rememberLazyListState()
    Column {
        LazyRow(
            contentPadding = PaddingValues(contentPadding),
            state = state,
            horizontalArrangement = Arrangement.spacedBy(paddingBetweenChildren)
        ) {
            items(items = itemList) { itemData ->
                itemContent(itemData)
            }
        }

        HorizontalScrollbar(
            modifier = Modifier.align(Alignment.CenterHorizontally).height(8.dp),
            adapter = rememberScrollbarAdapter(
                scrollState = state
            )
        )
    }
}