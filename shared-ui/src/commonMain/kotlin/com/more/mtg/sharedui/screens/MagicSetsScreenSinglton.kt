package com.more.mtg.sharedui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.more.mtg.sharedui.CardAspectRatio
import com.more.mtg.sharedui.models.LCE
import com.more.mtg.sharedui.widgets.HorizontalScrollingRow
import com.more.mtg.sharedui.widgets.LCEContent
import com.more.mtg.sharedui.widgets.MtgImage
import com.more.shareddata.network.provideScryFallService
import com.more.shareddata.network.scryfall.ScryfallMagicSet
import kotlinx.coroutines.async

object MagicSetsScreenSinglton: Screen {
    @Composable
    override fun Content() {
        MagicSetsScreen()
    }
}

@Composable
fun MagicSetsScreen() {
    var state: LCE<List<List<ScryfallMagicSet>>> by remember { mutableStateOf(LCE.Loading()) }
    val navigator = LocalNavigator.currentOrThrow
    LaunchedEffect("") {
        val officialSets =  async { provideScryFallService().getSets().data.filter { it.setType == "core" } }
        val latestSets =  async { provideScryFallService().getSets().data.sortedByDescending { it.releasedAt } }
        state = LCE.Content(listOf(officialSets.await(), latestSets.await()))
    }
    LCEContent(
        state = state,
        content = {setsList -> MagicSets(setsList) {setId -> navigator.push(MagicSetDetailScreen(setId))} },
        errorContent = {error -> Text("error") },
    )
}


@Composable
fun MagicSets(magicSets: List<List<ScryfallMagicSet>>, onSetClicked: (setId: String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Official Sets", style = MaterialTheme.typography.h5, modifier = Modifier.padding(start = 8.dp))
        HorizontalScrollingRow(
            itemList = magicSets[0],
            contentPadding = 8.dp,
            paddingBetweenChildren = 16.dp,
        ) {itemData ->
            MagicSet(itemData, onSetClicked)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Latest Sets", style = MaterialTheme.typography.h5, modifier = Modifier.padding(start = 8.dp))
        HorizontalScrollingRow(
            itemList = magicSets[1],
            contentPadding = 8.dp,
            paddingBetweenChildren = 16.dp,
        ) {itemData ->
            MagicSet(itemData, onSetClicked)
        }
    }
}

@Composable
fun MagicSet(magicSet: ScryfallMagicSet, onSetClicked: (setId: String) -> Unit) {
    Card (
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .width(180.dp)
            .aspectRatio(CardAspectRatio)
            .clickable { onSetClicked(magicSet.id) },
        elevation = 8.dp
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            MtgImage(
                url = magicSet.iconSvgUri,
                contentDescription = magicSet.name,
                modifier = Modifier.fillMaxWidth().aspectRatio(1f).padding(20.dp)
            )
            Text(
                magicSet.name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

