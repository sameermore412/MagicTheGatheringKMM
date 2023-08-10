package com.more.mtg.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.more.mtg.sharedui.screens.MagicCardDetailScreen
import com.more.mtg.sharedui.screens.MagicSetsScreen


class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //MagicCardDetailScreen()
            MagicSetsScreen()
        }
    }
}