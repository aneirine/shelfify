package com.aneirine.shelfify

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ShelfifyApplication

fun main(args: Array<String>) {
    runApplication<ShelfifyApplication>(*args)
}
