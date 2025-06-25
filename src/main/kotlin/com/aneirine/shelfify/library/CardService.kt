package com.aneirine.shelfify.library

import com.aneirine.shelfify.library.model.Card
import com.aneirine.shelfify.library.model.request.CardDto
import java.time.Instant
import java.util.*
import org.springframework.stereotype.Service

@Service
class CardService(private val libraryStorageService: LibraryStorageService) {

    // todo convert to dto
    fun getCards(): List<Card> = libraryStorageService.getLibrary().cards

    // todo convert to dto
    fun getCardsSorted(): List<Card> {
        val cards = libraryStorageService.getLibrary().cards
        cards.sortBy { it.createTime }
        return cards
    }

    fun saveCard(cardDto: CardDto) {
        if (cardDto.cardId == null) {
            val card = Card(UUID.randomUUID(), cardDto.title, cardDto.coverUrl, cardDto.description, Instant.now())
            libraryStorageService.saveToLibrary(card)
        } else {
            updateCard(cardDto)
        }
    }

    private fun updateCard(cardDto: CardDto) {
        val card = cardDto.cardId?.let { getCardById(it) }
        if (card != null) {
            card.title = cardDto.title
            card.description = cardDto.description
            card.coverUrl = cardDto.coverUrl
            libraryStorageService.saveToLibrary(card)
        }
    }

    fun getCardById(id: UUID): Card? {
        return libraryStorageService.getLibrary().cards.find { it.id == id }
    }

    fun deleteCardById(id: UUID) {
        libraryStorageService.deleteFromLibrary(id)
    }

}