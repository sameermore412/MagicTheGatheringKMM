package com.more.mtg.sharedui.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
expect fun <T> HorizontalScrollingRow(itemList: List<T>,
                                      contentPadding: Dp = 8.dp,
                                      paddingBetweenChildren: Dp = 8.dp,
                                      itemContent: @Composable (item: T) -> Unit)

@Composable
expect fun <T> ScrollingColumn(itemList: List<T>,
                               contentPadding: Dp = 8.dp,
                               paddingBetweenChildren: Dp = 8.dp,
                               itemContent: @Composable (item: T) -> Unit)