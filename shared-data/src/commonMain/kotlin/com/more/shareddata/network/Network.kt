package com.more.shareddata.network

import com.more.shareddata.network.scryfall.ScryfallService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


val httpClient = HttpClient {
    expectSuccess = true
    install(Logging) {
        logger = Logger.SIMPLE
        level = LogLevel.HEADERS
    }
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }
        )
    }
}

fun provideScryFallService() = ScryfallService(client = httpClient)