package com.aneirine.shelfify.library.model.request

import java.util.UUID

data class CardDto(
    val cardId: UUID?,
    val title: String,
    val coverUrl: String,
    val description: String
)
