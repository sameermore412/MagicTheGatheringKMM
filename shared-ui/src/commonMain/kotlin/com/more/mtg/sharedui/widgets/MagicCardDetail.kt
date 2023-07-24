package com.more.mtg.sharedui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun MagicCardDetail(magicCard: MagicCard = testCard, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = modifier.fillMaxSize()) {
            Box(modifier = Modifier.zIndex(1000f)) {
                HeroImage(magicCard)
                MiniCardImage(magicCard, modifier = Modifier
                    .zIndex(1000f)
                    .align(Alignment.BottomStart)
                    .padding(start = 8.dp)
                    .absoluteOffset(y = (MiniCardWidth / 2).dp))
            }
            Column(modifier = Modifier.absoluteOffset(x = (MiniCardWidth + 16).dp).zIndex(0f)) {
                Text(magicCard.cardName)
                Text(magicCard.flavorText)
            }
            Column(modifier = Modifier.padding(start = 8.dp, top = 16.dp, end = 8.dp).zIndex(0f)) {
                Text(magicCard.oracleText)
                if (magicCard.oracleText.isNotBlank()) {
                    Divider(
                        thickness = 1.dp,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Text(magicCard.flavorText, fontStyle = FontStyle.Italic)
            }
        }
    }
}

@Composable
fun HeroImage(magicCard: MagicCard,  modifier: Modifier = Modifier) {
    MtgCardImage(magicCard.artCropUrl, modifier = modifier
        .fillMaxWidth()
        .aspectRatio(1/CardAspectRatio)
    )
}

@Composable
fun MiniCardImage(magicCard: MagicCard, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        MtgCardImage(
            magicCard.borderCropUrl,
            modifier = Modifier.width(MiniCardWidth.dp)
                .aspectRatio(CardAspectRatio)
                .background(Color.Red))
    }
}

@Composable
fun MtgCardImage(url: String, modifier: Modifier = Modifier) {
    val painter = asyncPainterResource(data = url) {
    }

    KamelImage(resource = painter,
        contentDescription = "",
        contentScale = ContentScale.FillWidth,
        modifier = modifier)
}


const val MiniCardWidth = 96
const val CardAspectRatio = 5/7f

data class MagicCard(
    val cardName: String,
    val flavorText: String,
    val oracleText: String,
    val manaCost: String,
    val artCropUrl: String,
    val largeUrl: String,
    val borderCropUrl: String,
)

val testCard = MagicCard(
    cardName = "Sandwurm Convergence",
    manaCost = "{6}{G}{G}",
    oracleText = "Creatures with flying can't attack you or planeswalkers you control.\nAt the beginning of your end step, create a 5/5 green Wurm creature token.",
    flavorText = "Cantankerous and territorial, sandwurms claim even the skies above their dunes.",
    artCropUrl = "https://cards.scryfall.io/art_crop/front/9/4/94d2a7e4-acc9-4236-a78f-c3171fd0c9ec.jpg",
    largeUrl = "https://cards.scryfall.io/large/front/9/4/94d2a7e4-acc9-4236-a78f-c3171fd0c9ec.jpg",
    borderCropUrl = "https://cards.scryfall.io/border_crop/front/9/4/94d2a7e4-acc9-4236-a78f-c3171fd0c9ec.jpg"
)
