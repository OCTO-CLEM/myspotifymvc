package fr.mvc.myspotify.domain

interface MySpotifyRepository {
    fun findMyBestArtists(type: String): List<Artist>
}
