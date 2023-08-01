package com.more.shareddata.network.scryfall

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScryfallMagicCard(
    @SerialName("object") var obj: String? = null,
    @SerialName("id") var id: String? = null,
    @SerialName("oracle_id") var oracleId: String? = null,
    @SerialName("multiverse_ids") var multiverseIds: ArrayList<Int> = arrayListOf(),
    @SerialName("mtgo_id") var mtgoId: Int? = null,
    @SerialName("mtgo_foil_id") var mtgoFoilId: Int? = null,
    @SerialName("tcgplayer_id") var tcgplayerId: Int? = null,
    @SerialName("cardmarket_id") var cardmarketId: Int? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("lang") var lang: String? = null,
    @SerialName("released_at") var releasedAt: String? = null,
    @SerialName("uri") var uri: String? = null,
    @SerialName("scryfall_uri") var scryfallUri: String? = null,
    @SerialName("layout") var layout: String? = null,
    @SerialName("highres_image") var highresImage: Boolean? = null,
    @SerialName("image_status") var imageStatus: String? = null,
    @SerialName("image_uris") var imageUris: ImageUris? = ImageUris(),
    @SerialName("mana_cost") var manaCost: String? = null,
    @SerialName("cmc") var cmc: Float? = null,
    @SerialName("type_line") var typeLine: String? = null,
    @SerialName("oracle_text") var oracleText: String? = null,
    @SerialName("colors") var colors: ArrayList<String> = arrayListOf(),
    @SerialName("color_identity") var colorIdentity: ArrayList<String> = arrayListOf(),
    @SerialName("keywords") var keywords: ArrayList<String> = arrayListOf(),
    @SerialName("legalities") var legalities: Legalities? = Legalities(),
    @SerialName("games") var games: ArrayList<String> = arrayListOf(),
    @SerialName("reserved") var reserved: Boolean? = null,
    @SerialName("foil") var foil: Boolean? = null,
    @SerialName("nonfoil") var nonfoil: Boolean? = null,
    @SerialName("finishes") var finishes: ArrayList<String> = arrayListOf(),
    @SerialName("oversized") var oversized: Boolean? = null,
    @SerialName("promo") var promo: Boolean? = null,
    @SerialName("reprint") var reprint: Boolean? = null,
    @SerialName("variation") var variation: Boolean? = null,
    @SerialName("set_id") var setId: String? = null,
    @SerialName("set") var set: String? = null,
    @SerialName("set_name") var setName: String? = null,
    @SerialName("set_type") var setType: String? = null,
    @SerialName("set_uri") var setUri: String? = null,
    @SerialName("set_search_uri") var setSearchUri: String? = null,
    @SerialName("scryfall_set_uri") var scryfallSetUri: String? = null,
    @SerialName("rulings_uri") var rulingsUri: String? = null,
    @SerialName("prints_search_uri") var printsSearchUri: String? = null,
    @SerialName("collector_number") var collectorNumber: String? = null,
    @SerialName("digital") var digital: Boolean? = null,
    @SerialName("rarity") var rarity: String? = null,
    @SerialName("flavor_text") var flavorText: String? = null,
    @SerialName("card_back_id") var cardBackId: String? = null,
    @SerialName("artist") var artist: String? = null,
    @SerialName("artist_ids") var artistIds: ArrayList<String> = arrayListOf(),
    @SerialName("illustration_id") var illustrationId: String? = null,
    @SerialName("border_color") var borderColor: String? = null,
    @SerialName("frame") var frame: String? = null,
    @SerialName("full_art") var fullArt: Boolean? = null,
    @SerialName("textless") var textless: Boolean? = null,
    @SerialName("booster") var booster: Boolean? = null,
    @SerialName("story_spotlight") var storySpotlight: Boolean? = null,
    @SerialName("edhrec_rank") var edhrecRank: Int? = null,
    @SerialName("prices") var prices: Prices? = Prices(),
    @SerialName("related_uris") var relatedUris: RelatedUris? = RelatedUris(),
    @SerialName("purchase_uris") var purchaseUris: PurchaseUris? = PurchaseUris()
)

@Serializable
data class ImageUris(
    @SerialName("small") var small: String? = null,
    @SerialName("normal") var normal: String? = null,
    @SerialName("large") var large: String? = null,
    @SerialName("png") var png: String? = null,
    @SerialName("art_crop") var artCrop: String? = null,
    @SerialName("border_crop") var borderCrop: String? = null
)

@Serializable
data class Legalities(
    @SerialName("standard") var standard: String? = null,
    @SerialName("future") var future: String? = null,
    @SerialName("historic") var historic: String? = null,
    @SerialName("gladiator") var gladiator: String? = null,
    @SerialName("pioneer") var pioneer: String? = null,
    @SerialName("explorer") var explorer: String? = null,
    @SerialName("modern") var modern: String? = null,
    @SerialName("legacy") var legacy: String? = null,
    @SerialName("pauper") var pauper: String? = null,
    @SerialName("vintage") var vintage: String? = null,
    @SerialName("penny") var penny: String? = null,
    @SerialName("commander") var commander: String? = null,
    @SerialName("brawl") var brawl: String? = null,
    @SerialName("historicbrawl") var historicbrawl: String? = null,
    @SerialName("alchemy") var alchemy: String? = null,
    @SerialName("paupercommander") var paupercommander: String? = null,
    @SerialName("duel") var duel: String? = null,
    @SerialName("oldschool") var oldschool: String? = null,
    @SerialName("premodern") var premodern: String? = null
)

@Serializable
data class Prices(
    @SerialName("usd") var usd: String? = null,
    @SerialName("usd_foil") var usdFoil: String? = null,
    @SerialName("usd_etched") var usdEtched: String? = null,
    @SerialName("eur") var eur: String? = null,
    @SerialName("eur_foil") var eurFoil: String? = null,
    @SerialName("tix") var tix: String? = null
)

@Serializable
data class RelatedUris(
    @SerialName("gatherer") var gatherer: String? = null,
    @SerialName("tcgplayer_infinite_articles") var tcgplayerInfiniteArticles: String? = null,
    @SerialName("tcgplayer_infinite_decks") var tcgplayerInfiniteDecks: String? = null,
    @SerialName("edhrec") var edhrec: String? = null,
    @SerialName("mtgtop8") var mtgtop8: String? = null
)

@Serializable
data class PurchaseUris(
    @SerialName("tcgplayer") var tcgplayer: String? = null,
    @SerialName("cardmarket") var cardmarket: String? = null,
    @SerialName("cardhoarder") var cardhoarder: String? = null
)

