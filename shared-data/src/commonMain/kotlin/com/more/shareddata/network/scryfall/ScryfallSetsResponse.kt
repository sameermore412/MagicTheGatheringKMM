package com.more.shareddata.network.scryfall

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScryfallSetsResponse(
    @SerialName("object") val obj: String,
    @SerialName("has_more") val hasMore: Boolean,
    val data: List<ScryfallMagicSet>,
)