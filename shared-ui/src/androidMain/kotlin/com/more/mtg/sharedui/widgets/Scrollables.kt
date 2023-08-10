package com.more.mtg.sharedui.widgets
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
actual fun <T> HorizontalScrollingRow(itemList: List<T>,
                                      contentPadding: Dp,
                                      paddingBetweenChildren: Dp,
                                      itemContent: @Composable (item: T) -> Unit) {
    Column {
        LazyRow(
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = itemList) { itemData ->
                itemContent(itemData)
            }
        }
    }
}