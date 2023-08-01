package com.more.shareddata.network.scryfall

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ScryfallService(val client: HttpClient) {
    val hostUrl = "https://api.scryfall.com"

    /**
     * Get a response object containing all the sets
     */
    suspend fun getSets(): ScryfallSetsResponse = client.get("${hostUrl}/sets").body()

    /**
     * Get one set that has the provided set code
     */
    suspend fun getSetForSetCode(setCode: String): ScryfallMagicSet = getSetForQueryParam(setCode)

    /**
     * Get one set for the provided step id
     */
    suspend fun getSetForSetId(setId: String): ScryfallMagicSet = getSetForQueryParam(setId)


    private suspend fun getSetForQueryParam(queryParam: String): ScryfallMagicSet {
        return client.get("${hostUrl}/sets/$queryParam").body()
    }

    /**
     * Get a response object containing a list of magic cards.
     */
    suspend fun getCardsFromSearch(searchUri: String): ScryfallCardSearchResponse {
        return client.get(searchUri).body()
    }

    suspend fun getCard(cardId: String): ScryfallMagicCard {
        return client.get("${hostUrl}/cards/${cardId}").body()
    }

    suspend fun getRandomCard(): ScryfallMagicCard {
        return client.get("${hostUrl}/cards/random").body()
    }
}