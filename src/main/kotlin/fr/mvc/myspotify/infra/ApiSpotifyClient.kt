package fr.mvc.myspotify.infra

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import fr.mvc.myspotify.infra.TopArtistsResponse.TopArtistResponse
import org.springframework.stereotype.Service
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse


@Service
class ApiSpotifyClient {

    val mapper = jacksonObjectMapper()

    fun getUserTopItems(type: String): List<TopArtistResponse> = fetchMeTop(type).items.map { it }

    fun getArtists(id: String): ArtistResponse = fetchArtist(id)

    private fun fetchMeTop(type: String): TopArtistsResponse {
        val oauthToken =
            "BQCTA5-iPqZ5Db3JqRXpyoVdWP1UJgPeM-LJzupSyhG010VzVCoXjY0raxJohu6GIZYyw9V0oRLHT0cOfx8U94p8BYVdJ4YwOv0GlkOW7oaPd-3AzOHLreBtS5RwwHBM6CLmMS8YRGtd2YDk_qVL10t4dZqsi5pt2Zv_BcecL6K3gA"

        val client = HttpClient.newBuilder().build()
        val request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.spotify.com/v1/me/top/$type"))
            .GET()
            .header("Authorization", "Bearer $oauthToken")
            .build()

        val response = client.send(request, HttpResponse.BodyHandlers.ofString()).body()
        return mapper.readValue(response, TopArtistsResponse::class.java)
    }

    private fun fetchArtist(id: String): ArtistResponse {
        val oauthToken =
            "BQBCPUeBhEK77_OhZwXd9fhrjOjwEqmBDfTHZlFpWOEGDKP21jvb2sTX4oGh0_Q8TpIxFHW1Hlojzs8Qcw8KIk5oJNJPCo1oP3UGA05bdNv6Xxt0m7BX8zdt8aM6jYJMugjW0-qewnunnNowkrjFk_eU1r_EEThQFZveeH794SYZYg"

        val client = HttpClient.newBuilder().build()
        val request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.spotify.com/v1/artists/$id"))
            .GET()
            .header("Authorization", "Bearer $oauthToken")
            .build()

        val response = client.send(request, HttpResponse.BodyHandlers.ofString()).body()
        return mapper.readValue(response, ArtistResponse::class.java)
    }
}

data class TopArtistsResponse(
    val items: List<TopArtistResponse>
) {
    data class TopArtistResponse(
        val id: String,
        val name: String,
    )
}

data class ArtistResponse(
    val followers: ArtistFollowersResponse,
    val popularity: Int
) {
    data class ArtistFollowersResponse(
        val total: Int,
    )
}