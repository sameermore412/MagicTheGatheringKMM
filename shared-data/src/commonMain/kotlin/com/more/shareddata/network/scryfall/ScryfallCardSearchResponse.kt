package com.more.shareddata.network.scryfall

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScryfallCardSearchResponse(
    @SerialName("object") val obj: String,
    @SerialName("total_cards") val totalCards: Int,
    @SerialName("has_more") val hasMore: Boolean,
    @SerialName("next_page") val nextPage: String? = null,
    val data: List<ScryfallMagicCard>
)