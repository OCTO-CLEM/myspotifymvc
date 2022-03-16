package fr.mvc.myspotify.usecases

import fr.mvc.myspotify.domain.MySpotifyRepository
import org.springframework.stereotype.Service

@Service
class MySpotifyUseCase(
    private val mySpotifyRepository: MySpotifyRepository
) {
    operator fun invoke(type: String) = mySpotifyRepository.findMyBestArtists(type)
}