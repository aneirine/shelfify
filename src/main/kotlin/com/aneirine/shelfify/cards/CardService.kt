package com.aneirine.shelfify.cards

import com.aneirine.shelfify.cards.model.Card
import com.aneirine.shelfify.cards.model.request.CardDto
import org.springframework.stereotype.Service
import java.util.*

@Service
class CardService {

    private val cards = mutableListOf(
        Card(
            UUID.randomUUID(),
            "Some Book",
            "https://img.freepik.com/free-photo/closeup-scarlet-macaw-from-side-view-scarlet-macaw-closeup-head_488145-3540.jpg?semt=ais_hybrid&w=740"
        ),
        Card(
            UUID.randomUUID(),
            "Another Game",
            "https://images.unsplash.com/photo-1593696954577-ab3d39317b97?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTh8fGZyZWUlMjBpbWFnZXN8ZW58MHx8MHx8fDA%3D"
        ),
    )

    fun getCards(): List<Card> = cards

    fun addCard(cardDto: CardDto): List<Card> {
        val card = Card(UUID.randomUUID(), cardDto.title, cardDto.coverUrl)
        cards.add(card)
        return cards
    }


}