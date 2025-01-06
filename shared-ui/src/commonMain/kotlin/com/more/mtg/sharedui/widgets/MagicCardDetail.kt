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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.svg.SvgDecoder
import com.more.mtg.sharedui.CardAspectRatio
import com.more.mtg.sharedui.MiniCardWidth
import com.more.shareddata.ScryfallSymbol
import com.more.shareddata.ScryfallSymbolsStore

@Composable
fun MagicCardDetail(magicCard: MagicCard = testCard, modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    Column(modifier = modifier.fillMaxSize().verticalScroll(scrollState)) {
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
            ManaCost(magicCard.manaCost)
        }
        Column(modifier = Modifier.padding(start = 8.dp, top = 16.dp, end = 8.dp).zIndex(0f)) {
            OracleText(magicCard.oracleText)
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

@Composable
fun ManaCost(text: String) {
    TextWithInlineImages(
        text = text,
        symbols = ScryfallSymbolsStore.keys(),
        inlineContentMap = ScryfallSymbolsStore.getMap().toInlineTextContentMap(::toInlineTextContent)
    )
}

@Composable
fun OracleText(text: String) {
    TextWithInlineImages(
        text = text,
        symbols = ScryfallSymbolsStore.keys(),
        inlineContentMap = ScryfallSymbolsStore.getMap().toInlineTextContentMap(::toInlineTextContent)
    )
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
    AsyncImage(
        model = ImageRequest
            .Builder(LocalPlatformContext.current)
            .data(url)
            .build(),
        contentDescription = "",
        placeholder = ColorPainter(Color.Red),
        contentScale = ContentScale.FillWidth,
        modifier = modifier
    )
}

@Composable
fun MtgImage(url: String, contentDescription: String = "", modifier: Modifier = Modifier) {
    ImageLoader.Builder(LocalPlatformContext.current)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()

    AsyncImage(
        model = ImageRequest
            .Builder(LocalPlatformContext.current)
            .data(url)
            .build(),
        contentDescription = contentDescription,
        placeholder = ColorPainter(Color.Red),
        contentScale = ContentScale.FillWidth,
        modifier = modifier
    )
}

data class MagicCard(
    val cardName: String,
    val flavorText: String,
    val oracleText: String,
    val manaCost: String,
    val artCropUrl: String,
    val largeUrl: String,
    val borderCropUrl: String,
)

fun toInlineTextContent(symbol: ScryfallSymbol): InlineTextContent {
    return InlineTextContent(
        Placeholder(
            width = 16.sp,
            height = 16.sp,
            placeholderVerticalAlign = PlaceholderVerticalAlign.Center
        )
    ) {
        MtgImage(symbol.svgUri ?: "", modifier = Modifier)
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
