package com.aneirine.shelfify.library.model

data class LibraryData(
    val cards: MutableList<Card> = mutableListOf()
)