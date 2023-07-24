package com.more.mtg.sharedui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.more.mtg.sharedui.widgets.MagicCardDetail
import com.more.mtg.sharedui.widgets.MagicLoadingIndicator

@Composable
fun MagicCardDetailScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.align(Alignment.Center)) {
            MagicLoadingIndicator()
        }
        MagicCardDetail()
    }
}