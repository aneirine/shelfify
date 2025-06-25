package com.aneirine.shelfify.tags

import java.util.UUID

data class Tag(
    val id: UUID,
    var title: String,
    var colorClass: String
)
