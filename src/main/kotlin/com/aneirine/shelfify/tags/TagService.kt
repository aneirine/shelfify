package com.aneirine.shelfify.tags

import java.util.*
import kotlin.math.abs
import org.springframework.stereotype.Service

@Service
class TagService {

    private val tags = mutableListOf(
        Tag(UUID.randomUUID(), "film"),
        Tag(UUID.randomUUID(), "book"),
        Tag(UUID.randomUUID(), "game"),
        Tag(UUID.randomUUID(), "music"),
    )

    fun getTags(): List<Tag> = tags

    fun getTagColorMap(tags: Collection<Tag>): Map<UUID, String> {
        val colorClasses =
            listOf("bg-blue-500", "bg-green-500", "bg-purple-500", "bg-pink-500", "bg-yellow-500", "bg-red-500")

        val tagColorMap = tags.associate { tag ->
            tag.id to colorClasses[abs(tag.title.hashCode()) % colorClasses.size]
        }

        return tagColorMap;
    }
}