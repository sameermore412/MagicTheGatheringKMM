package com.more.mtg.sharedui.widgets

import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle

@Composable
fun TextWithInlineImages(text: String,
                         symbols: Set<String>,
                         inlineContentMap: Map<String, InlineTextContent>,
                         modifier: Modifier = Modifier,
                         fontStyle: FontStyle = FontStyle.Normal) {
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
        inlineContent = inlineContentMap
    )
}