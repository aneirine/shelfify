package com.aneirine.shelfify.cards.model

import java.util.UUID


data class Card(
    val id: UUID,
    val title: String,
    val coverUrl: String
)
