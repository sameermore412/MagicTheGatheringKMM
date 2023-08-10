package com.more.mtg

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.more.mtg.sharedui.screens.MagicCardDetailScreen
import com.more.mtg.sharedui.screens.MagicSetsScreen
import com.more.mtg.sharedui.widgets.MagicCardDetail
import com.more.mtg.sharedui.widgets.MagicLoadingIndicator
import com.more.mtg.sharedui.widgets.testCard

fun main() = application {
    Window(
        title = "Sample App",
        onCloseRequest = ::exitApplication,
    ) {
        //MagicCardDetailScreen()
        MagicSetsScreen()
    }
}

@Preview
@Composable
fun PreviewMagicLoadingIndicator() {
    Column() {
        Text("hi")
        MagicLoadingIndicator()
    }
}