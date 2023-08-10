package com.more.mtg.sharedui.widgets


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.more.mtg.sharedui.models.LCE

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun <T> LCEContent(state: LCE<T>,
                   content: @Composable BoxScope.(T) -> Unit,
                   errorContent: @Composable BoxScope.(LCE.Error<T>) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)

    ) {
        var visible by remember { mutableStateOf(true) }
        Box(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.align(Alignment.Center).fillMaxSize()) {
                when (state) {
                    is LCE.Content -> {
                        visible = false
                        content(state.data)
                    }
                    is LCE.Error -> {
                        visible = false
                        errorContent(state)
                    }
                    is LCE.Loading -> {
                        visible = true
                    }
                }
            }
            androidx.compose.animation.AnimatedVisibility(
                visible = visible,
                enter = scaleIn(),
                exit = scaleOut(),
                modifier = Modifier.align(Alignment.Center)
            ) {
                MagicLoadingIndicator()
            }
        }
    }
}