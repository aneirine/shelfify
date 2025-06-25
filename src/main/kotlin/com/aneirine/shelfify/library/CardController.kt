package com.aneirine.shelfify.library

import com.aneirine.shelfify.library.model.Card
import com.aneirine.shelfify.library.model.request.CardDto
import com.aneirine.shelfify.tags.TagService
import java.util.*
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class CardController(
    private val cardService: CardService,
    private val tagService: TagService
) {

    @GetMapping("/library")
    fun getCards(model: Model): String {
        val cards = cardService.getCardsSorted()
        val tags = tagService.getTags()
        val tagColorMap = tagService.getTagColorMap(tags);

        model.addAttribute("cards", cards)
        model.addAttribute("tags", tags)
        model.addAttribute("tagColorMap", tagColorMap)
        return "library"
    }

    @PostMapping("/library/card")
    fun addCard(cardDto: CardDto, redirectAttributes: RedirectAttributes): String {
        cardService.saveCard(cardDto);
        redirectAttributes.addFlashAttribute("message", "New card was added")
        return "redirect:/library"
    }

    @GetMapping("/library/card/{id}")
    @ResponseBody
    fun getCardById(@PathVariable id: UUID): Card? {
        return cardService.getCardById(id)
    }

    @DeleteMapping("/library/card/{id}")
    fun deleteCardById(@PathVariable id: UUID): ResponseEntity<Void> {
        cardService.deleteCardById(id)
        return ResponseEntity.noContent().build()
    }

}