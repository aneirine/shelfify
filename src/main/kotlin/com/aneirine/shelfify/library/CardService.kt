package com.aneirine.shelfify.library

import com.aneirine.shelfify.library.model.Card
import com.aneirine.shelfify.library.model.request.CardDto
import java.util.*
import org.springframework.stereotype.Service

@Service
class CardService(private val libraryStorageService: LibraryStorageService) {

    fun getCards(): List<Card> = libraryStorageService.getLibrary().cards

    fun addCard(cardDto: CardDto): List<Card> {
        val card = Card(UUID.randomUUID(), cardDto.title, cardDto.coverUrl, cardDto.description)
        libraryStorageService.saveToLibrary(card)
        return libraryStorageService.getLibrary().cards
    }

    fun getCardById(id: UUID): Card? {
        return libraryStorageService.getLibrary().cards.find { it.id == id }
    }

}