package com.more.mtg.sharedui.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

@Composable
expect fun <T> HorizontalScrollingRow(itemList: List<T>,
                                      contentPadding: Dp,
                                      paddingBetweenChildren: Dp,
                                      itemContent: @Composable (item: T) -> Unit)

//@Composable
//actual fun <T> HorizontalScrolling(itemList: List<T>, itemContent: @Composable (item: T) -> Unit) {
//    val state = rememberLazyListState()
//    Column {
//        LazyRow(
//            contentPadding = PaddingValues(8.dp),
//            horizontalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            items(items = itemList) { itemData ->
//                itemContent(itemData)
//            }
//        }
//
//        HorizontalScrollbar(
//            modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxHeight(),
//            adapter = rememberScrollbarAdapter(
//                scrollState = state
//            )
//        )
//    }
//}