package com.aneirine.shelfify.library

import com.aneirine.shelfify.library.model.Card
import com.aneirine.shelfify.library.model.LibraryData
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File
import org.springframework.stereotype.Service

@Service
class LibraryStorageService {

    private val fileName = "shelfify.json";
    private val file = File(fileName)
    private val mapper = jacksonObjectMapper()

    private var libraryData: LibraryData = loadLibraryFromFile()

    fun getLibrary(): LibraryData = libraryData

    fun saveToLibrary(card: Card) {
        libraryData.cards.add(card)
        saveLibraryToFile()
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