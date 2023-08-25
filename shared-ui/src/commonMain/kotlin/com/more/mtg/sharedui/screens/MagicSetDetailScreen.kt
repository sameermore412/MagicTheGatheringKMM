package com.more.mtg.sharedui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.more.mtg.sharedui.CardAspectRatio
import com.more.mtg.sharedui.models.LCE
import com.more.mtg.sharedui.widgets.LCEContent
import com.more.mtg.sharedui.widgets.MtgImage
import com.more.mtg.sharedui.widgets.ScrollingColumn
import com.more.shareddata.network.provideScryFallService
import com.more.shareddata.network.scryfall.ScryfallMagicCard
import kotlinx.coroutines.async

data class MagicSetDetailScreen(val setId: String): Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var state: LCE<List<ScryfallMagicCard>> by remember { mutableStateOf(LCE.Loading()) }
        LaunchedEffect("") {
            val set = provideScryFallService().getSetForSetId(setId)
            val cards =  async { provideScryFallService().getCardsFromSearch(set.searchUri).data }
            state = LCE.Content(cards.await())
        }

        LCEContent(
            state = state,
            content = {cards ->
                MagicSetDetailContent(
                    cards = cards,
                    onCardClicked = {cardId -> navigator.push(MagicCardDetailScreen(cardId))},
                    onBackClicked = {navigator.pop()}) },
            errorContent = {error -> Text("error") },
        )
    }
}

@Composable
fun MagicSetDetailContent(cards: List<ScryfallMagicCard>, onBackClicked: () -> Unit, onCardClicked: (String) -> Unit) {
    ScrollingColumn(cards, itemContent = { magicCard ->
        magicCard.imageUris?.borderCrop?.let { url ->
            Box(modifier = Modifier.clickable { onCardClicked(magicCard.id!!) }) {
                MtgImage(url, modifier = Modifier.width(200.dp).aspectRatio(CardAspectRatio))
            }
        }
    })
}