package fr.mvc.myspotify.infra

import fr.mvc.myspotify.domain.Artist
import fr.mvc.myspotify.infra.TopArtistsResponse.TopArtistResponse
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ApiMySpotifyRepositoryTest {

    @Mock
    private lateinit var apiSpotifyClient: ApiSpotifyClient

    @InjectMocks
    private lateinit var apiMySpotifyRepository: ApiMySpotifyRepository

    @Test
    fun `it gets details from other api and then returns my best artists`() {
        given(apiSpotifyClient.getUserTopItems("artists")).willReturn(
            listOf(
                TopArtistResponse("123456789", "Nekfeu"),
                TopArtistResponse("101121214", "Alpha Wann"),
                TopArtistResponse("1516171819", "Freeze Corleone"),
            )
        )
        given(apiSpotifyClient.getArtists("123456789")).willReturn(
            ArtistResponse(ArtistResponse.ArtistFollowersResponse(100000), 100)
        )
        given(apiSpotifyClient.getArtists("101121214")).willReturn(
            ArtistResponse(ArtistResponse.ArtistFollowersResponse(90000), 90)
        )
        given(apiSpotifyClient.getArtists("1516171819")).willReturn(
            ArtistResponse(ArtistResponse.ArtistFollowersResponse(95000), 95)
        )

        val result = apiMySpotifyRepository.findMyBestArtists("artists")

        assertEquals(
            listOf(
                Artist("123456789", "Nekfeu", 100000, 100),
                Artist("101121214", "Alpha Wann", 90000, 90),
                Artist("1516171819", "Freeze Corleone", 95000, 95),
            ),
            result
        )
    }
}