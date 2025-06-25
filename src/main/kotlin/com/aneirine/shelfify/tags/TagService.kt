package com.aneirine.shelfify.tags

import com.aneirine.shelfify.library.LibraryStorageService
import java.util.*
import org.springframework.stereotype.Service

@Service
class TagService(private val libraryStorageService: LibraryStorageService) {

    fun getTags(): List<Tag> = libraryStorageService.getLibrary().tags

    fun getTagColorMap(tags: Collection<Tag>): Map<UUID, String> {
        return tags.associate { tag ->
            tag.id to tag.colorClass
        }
    }
}