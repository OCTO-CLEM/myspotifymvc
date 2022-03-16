package fr.mvc.myspotify.infra

import fr.mvc.myspotify.domain.Artist
import fr.mvc.myspotify.domain.MySpotifyRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.Date

@Repository
class ApiMySpotifyRepository(
    private val apiSpotifyClient: ApiSpotifyClient
) : MySpotifyRepository {
    override fun findMyBestArtists(type: String): List<Artist> =
        apiSpotifyClient.getUserTopItems(type).map { topArtistResponse ->
            val artistDetail = apiSpotifyClient.getArtists(topArtistResponse.id)
            Artist(
                id = topArtistResponse.id,
                name = topArtistResponse.name,
                followerNumbers = artistDetail.followers.total,
                popularity = artistDetail.popularity
            )
        }.also {
            println("${LocalDateTime.now().hour}:${LocalDateTime.now().minute}:${LocalDateTime.now().second} [${Thread.currentThread().name}] INFO - ${it.toString()} ")
        }
}