package com.more.mtg.sharedui.widgets
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

@Composable
actual fun <T> HorizontalScrollingRow(itemList: List<T>,
                                      contentPadding: Dp,
                                      paddingBetweenChildren: Dp,
                                      itemContent: @Composable (item: T) -> Unit) {
    Column {
        LazyRow(
            contentPadding = PaddingValues(contentPadding),
            horizontalArrangement = Arrangement.spacedBy(paddingBetweenChildren)
        ) {
            items(items = itemList) { itemData ->
                itemContent(itemData)
            }
        }
    }
}

@Composable
actual fun <T> ScrollingColumn(itemList: List<T>,
                               contentPadding: Dp,
                               paddingBetweenChildren: Dp,
                               itemContent: @Composable (item: T) -> Unit) {
    Column {
        LazyColumn(
            contentPadding = PaddingValues(contentPadding),
            verticalArrangement = Arrangement.spacedBy(paddingBetweenChildren)
        ) {
            items(items = itemList) { itemData ->
                itemContent(itemData)
            }
        }
    }
}