package com.aneirine.shelfify.library.model

import com.aneirine.shelfify.tags.Tag

data class LibraryData(
    val cards: MutableList<Card> = mutableListOf(),
    val tags: MutableList<Tag> = mutableListOf()
)