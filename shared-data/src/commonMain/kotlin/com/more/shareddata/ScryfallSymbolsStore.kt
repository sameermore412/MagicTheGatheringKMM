package com.more.shareddata

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

object ScryfallSymbolsStore {
    private val scryfallSymbolMap =
        Json.decodeFromString<List<ScryfallSymbol>>(symbolJson).associateBy { it.symbol ?: "" }

    fun findSymbolDef(symbol: String) = scryfallSymbolMap[symbol]
    fun keys() = scryfallSymbolMap.keys
    fun getMap() = scryfallSymbolMap
}

@Serializable
data class ScryfallSymbol(
    @SerialName("object") var obj: String? = null,
    @SerialName("symbol") var symbol: String? = null,
    @SerialName("svg_uri") var svgUri: String? = null,
    @SerialName("loose_variant") var looseVariant: String? = null,
    @SerialName("english") var english: String? = null,
    @SerialName("transposable") var transposable: Boolean? = null,
    @SerialName("represents_mana") var representsMana: Boolean? = null,
    @SerialName("appears_in_mana_costs") var appearsInManaCosts: Boolean? = null,
    @SerialName("cmc") var cmc: Double? = null,
    @SerialName("funny") var funny: Boolean? = null,
    @SerialName("colors") var colors: ArrayList<String> = arrayListOf(),
    @SerialName("gatherer_alternates") var gathererAlternates: List<String>? = null
)