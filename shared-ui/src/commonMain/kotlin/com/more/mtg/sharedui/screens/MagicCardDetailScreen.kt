package com.more.mtg.sharedui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.more.mtg.sharedui.widgets.MagicCardDetail
import com.more.mtg.sharedui.widgets.MagicLoadingIndicator
import kotlinx.coroutines.delay

@Composable
fun MagicCardDetailScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        var isLoadingHidden by remember { mutableStateOf(false) }
        LaunchedEffect("") {
            delay(2000)
            isLoadingHidden = true

        }
        MagicCardDetail()
        Box(modifier = Modifier.align(Alignment.Center)) {
            AnimatedVisibility(!isLoadingHidden) {
                MagicLoadingIndicator()
            }
        }
    }
}