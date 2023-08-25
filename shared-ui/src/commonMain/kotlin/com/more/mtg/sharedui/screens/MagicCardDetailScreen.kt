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
import cafe.adriel.voyager.core.screen.Screen
import com.more.mtg.sharedui.widgets.MagicCard
import com.more.mtg.sharedui.widgets.MagicCardDetail
import com.more.mtg.sharedui.widgets.MagicLoadingIndicator
import com.more.shareddata.network.provideScryFallService
import com.more.shareddata.network.scryfall.ScryfallMagicCard

data class MagicCardDetailScreen(val cardId: String): Screen {
    @Composable
    override fun Content() {
        Box(modifier = Modifier.fillMaxSize()) {
            var isLoadingHidden by remember { mutableStateOf(false) }
            var card by remember { mutableStateOf<ScryfallMagicCard?>(null) }
            LaunchedEffect("") {
                card = provideScryFallService().getCard(cardId)
                isLoadingHidden = true
            }
            card?.let {
                MagicCardDetail(
                    magicCard = MagicCard(
                    cardName = it.name!!,
                    flavorText = it.let { it.flavorText }?: "",
                    oracleText = it.oracleText!!,
                    manaCost = it.manaCost!!,
                    artCropUrl = it.imageUris!!.artCrop!!,
                    largeUrl = it.imageUris!!.large!!,
                    borderCropUrl = it.imageUris!!.borderCrop!!
                    )
                )
            }
            Box(modifier = Modifier.align(Alignment.Center)) {
                AnimatedVisibility(!isLoadingHidden) {
                    MagicLoadingIndicator()
                }
            }
        }
    }
}