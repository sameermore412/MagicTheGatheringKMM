package com.more.shareddata.network.scryfall

import com.more.shareddata.network.provideScryFallService

class ScryfallRepository(private val scryfallService: ScryfallService) {

    suspend fun getMagicCardsInSet(setId: String): List<ScryfallMagicCard> {
        val scryfallSet = scryfallService.getSetForSetId(setId)
        var searchResponse = scryfallService.getCardsFromSearch(scryfallSet.searchUri)
        val cardList = searchResponse.data.toMutableList()
        cardList.addAll(searchResponse.data)

        while (searchResponse.hasMore && searchResponse.nextPage != null) {
            searchResponse = scryfallService.getCardsFromSearch(searchResponse.nextPage!!)
            cardList.addAll(searchResponse.data)
        }
        return cardList
    }
}

fun provideScryfallRepository() = ScryfallRepository(provideScryFallService())