package com.more.mtg

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.more.mtg.sharedui.screens.MagicSetsScreenSinglton
import com.more.mtg.sharedui.widgets.MagicLoadingIndicator

fun main() = application {
    Navigator(MagicSetsScreenSinglton) { navigator ->
        Window(
            title = "Magic The Gathering App",
            onCloseRequest = ::exitApplication,
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(modifier = Modifier.fillMaxWidth().shadow(8.dp).height(64.dp).background(Color.Blue)) {
                    Button(onClick = {navigator.pop()}) {
                        Text("Back")
                    }
                }
                CurrentScreen()
            }
        }
    }
//
//    Window(
//        title = "Magic The Gathering App",
//        onCloseRequest = ::exitApplication,
//    ) {
//        Navigator(MagicSetsScreen) { navigator ->
//            Box(modifier = Modifier.fillMaxSize()) {
//                CurrentScreen()
//                Button(onClick = {navigator.pop()}, modifier = Modifier.align(Alignment.TopStart)) {
//                    Text("Back")
//                }
//            }
//
//        }
//    }
}

@Preview
@Composable
fun PreviewMagicLoadingIndicator() {
    Column() {
        Text("hi")
        MagicLoadingIndicator()
    }
}