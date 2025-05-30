package com.aneirine.shelfify.library.model

import java.util.UUID


data class Card(
    val id: UUID,
    val title: String,
    val coverUrl: String,
    val description: String,
)
