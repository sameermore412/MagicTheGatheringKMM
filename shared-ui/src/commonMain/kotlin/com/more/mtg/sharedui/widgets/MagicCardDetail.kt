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
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.more.shareddata.ScryfallSymbol
import com.more.shareddata.ScryfallSymbolsStore
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
                TextWithInlineImages(magicCard.manaCost)
            }
            Column(modifier = Modifier.padding(start = 8.dp, top = 16.dp, end = 8.dp).zIndex(0f)) {
                TextWithInlineImages(magicCard.oracleText)
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

@Composable
fun MtgImage(url: String, modifier: Modifier = Modifier) {
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

@Composable
fun TextWithInlineImages(text: String,
                         modifier: Modifier = Modifier,
                         fontStyle: FontStyle = FontStyle.Normal) {
    val symbols = ScryfallSymbolsStore.keys()
    Text(
        text = buildAnnotatedString {
            var match = text.findAnyOf(symbols)
            var start = 0
            while (match != null) {
                val begin = match.first
                val length = match.second.length

                append(text.substring(start, begin))
                appendInlineContent(text.substring(begin, begin + length))

                start = begin + length
                match = text.findAnyOf(symbols, start)
            }
            if (start < text.length) {
                append(text.substring(start))
            }
        },
        color = Color.Black,
        fontStyle = fontStyle,
        modifier  = modifier,
        inlineContent = ScryfallSymbolsStore.getMap().toInlineTextContentMap(::toInlineTextContent))
}

fun toInlineTextContent(symbol: ScryfallSymbol): InlineTextContent {
    return InlineTextContent(
        Placeholder(
            width = 16.sp,
            height = 16.sp,
            placeholderVerticalAlign = PlaceholderVerticalAlign.Center
        )
    ) {
        MtgImage(symbol.svgUri ?: "", Modifier)
    }
}

fun Map<String, ScryfallSymbol>.toInlineTextContentMap(builder: (ScryfallSymbol) -> InlineTextContent) = this.mapValues { entry ->
    builder(entry.value)
}

val testCard = MagicCard(
    cardName = "Aragorn, King of Gondor",
    manaCost = "{1}{U}{R}{W}",
    oracleText = "Vigilance, lifelink\nWhen Aragorn, King of Gondor enters the battlefield, you become the monarch.\nWhenever Aragorn attacks, up to one target creature can't block this turn. If you're the monarch, creatures can't block this turn.",
    flavorText = "\"In this place will I abide, and my heirs, unto the ending of the world.\"",
    artCropUrl = "https://cards.scryfall.io/art_crop/front/1/c/1c7ac04c-8510-468b-aa6f-f249bde9ff87.jpg",
    largeUrl = "https://cards.scryfall.io/large/front/1/c/1c7ac04c-8510-468b-aa6f-f249bde9ff87.jpg",
    borderCropUrl = "https://cards.scryfall.io/border_crop/front/1/c/1c7ac04c-8510-468b-aa6f-f249bde9ff87.jpg"
)