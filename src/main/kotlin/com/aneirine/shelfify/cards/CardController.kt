package com.aneirine.shelfify.cards

import com.aneirine.shelfify.cards.model.request.CardDto
import com.aneirine.shelfify.tags.TagService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class CardController(
    private val cardService: CardService,
    private val tagService: TagService
) {

    @GetMapping("/library")
    fun getCards(model: Model): String {
        val cards = cardService.getCards()
        val tags = tagService.getTags()
        val tagColorMap = tagService.getTagColorMap(tags);

        model.addAttribute("cards", cards)
        model.addAttribute("tags", tags)
        model.addAttribute("tagColorMap", tagColorMap)
        return "library"
    }

    @PostMapping("/library/card")
    fun addCard(cardDto: CardDto, redirectAttributes: RedirectAttributes): String {
        cardService.addCard(cardDto);
        redirectAttributes.addFlashAttribute("message", "New card was added")
        return "redirect:/library"
    }
}