package fr.mvc.myspotify.infra

import fr.mvc.myspotify.domain.Artist
import fr.mvc.myspotify.usecases.MySpotifyUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/my_spotify")
class MySpotifyController(private val mySpotifyUseCase: MySpotifyUseCase) {

    @GetMapping
    fun mySpotify(@RequestParam type: String): List<Artist> = mySpotifyUseCase(type)
}