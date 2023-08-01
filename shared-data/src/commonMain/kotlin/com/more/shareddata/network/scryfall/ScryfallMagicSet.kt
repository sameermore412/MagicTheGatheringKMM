package com.more.shareddata.network.scryfall

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScryfallMagicSet(
    @SerialName("object") val obj: String,
    val id: String,
    val code: String,
    val name: String,
    val uri: String,
    @SerialName("scryfall_uri") val scryFallUri: String,
    @SerialName("search_uri") val searchUri: String,
    @SerialName("released_at") val releasedAt: String,
    @SerialName("set_type") val setType: String,
    @SerialName("card_count") val cardCount: String,
    @SerialName("parent_set_code") val parentSetCode: String? = null,
    @SerialName("icon_svg_uri") val iconSvgUri: String,
)