package com.aneirine.shelfify.library.model

import java.time.Instant
import java.util.UUID


data class Card(
    val id: UUID,
    var title: String,
    var coverUrl: String,
    var description: String,
    var createTime: Instant
)
