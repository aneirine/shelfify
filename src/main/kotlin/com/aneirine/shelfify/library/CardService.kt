package com.aneirine.shelfify.library

import com.aneirine.shelfify.library.model.Card
import com.aneirine.shelfify.library.model.LibraryData
import com.aneirine.shelfify.library.model.request.CardDto
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File
import java.util.*
import org.springframework.stereotype.Service

@Service
class CardService {

    private val fileName = "shelfify.json";
    private val file = File(fileName)
    private val mapper = jacksonObjectMapper()

    private var libraryData: LibraryData = loadLibraryFromFile()

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

    fun getCards(): List<Card> = libraryData.cards

    fun addCard(cardDto: CardDto): List<Card> {
        val card = Card(UUID.randomUUID(), cardDto.title, cardDto.coverUrl, cardDto.description)
        libraryData.cards.add(card)
        saveLibraryToFile()
        return libraryData.cards
    }

    fun getCardById(id: UUID): Card? {
        return libraryData.cards.find { it.id == id }
    }

}