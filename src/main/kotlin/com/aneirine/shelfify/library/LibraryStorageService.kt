package com.aneirine.shelfify.library

import com.aneirine.shelfify.library.model.Card
import com.aneirine.shelfify.library.model.LibraryData
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File
import java.util.UUID
import org.springframework.stereotype.Service

@Service
class LibraryStorageService {

    private val fileName = "shelfify.json";
    private val file = File(fileName)
    private val mapper = objectMapper()

    private var libraryData: LibraryData = loadLibraryFromFile()

    private final fun objectMapper(): ObjectMapper {
        val objectMapper = jacksonObjectMapper()
            .registerModule(JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

        return objectMapper
    }

    fun getLibrary(): LibraryData = libraryData

    fun saveToLibrary(card: Card) {
        val existingIndex = libraryData.cards.indexOfFirst { it.id == card.id }
        if (existingIndex >= 0) {
            libraryData.cards[existingIndex] = card
        } else {
            libraryData.cards.add(card)
        }
        saveLibraryToFile()
    }

    fun deleteFromLibrary(cardId: UUID) {
        val removed = libraryData.cards.removeIf { it.id == cardId }
        if (removed) {
            saveLibraryToFile()
        }
    }

    private fun loadLibraryFromFile(): LibraryData {
        return if (file.exists()) {
            try {
                mapper.readValue(file)
            } catch (e: Exception) {
                LibraryData()
            }
        } else {
            LibraryData()
        }
    }

    private fun saveLibraryToFile() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, libraryData)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



}