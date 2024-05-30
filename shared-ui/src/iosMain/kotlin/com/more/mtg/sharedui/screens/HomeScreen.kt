package com.more.mtg.sharedui.screens

import androidx.compose.ui.window.ComposeUIViewController
import cafe.adriel.voyager.navigator.Navigator

fun HomeScreenViewController() = ComposeUIViewController {
    Navigator(MagicSetsScreenSinglton)
}