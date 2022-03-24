package fr.mvc.octuberfood.infra

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import fr.mvc.octuberfood.domain.users.UserLocation
import fr.mvc.octuberfood.infra.RestaurantsResponse.RestaurantResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Service
class ApiRestaurantClient {

    val mapper = jacksonObjectMapper()

    fun getRestaurants(userLocation: UserLocation): List<RestaurantResponse> =
        mapper.readValue(
            """
                    {
                      "items": [
                        {
                          "name": "belle asie",
                          "type": "asiatique"
                        },
                        {
                          "name": "los kebabos",
                          "type": "kebab"
                        },
                        {
                          "name": "ramen city",
                          "type": "asiatique"
                        },
                        {
                          "name": "la masse",
                          "type": "bistrot"
                        }
                      ]
                    }
                """.trimIndent(),
            RestaurantsResponse::class.java
        ).items

    fun getBestRatedRestaurants(userLocation: UserLocation): List<RestaurantResponse> =
        mapper.readValue(
            """
                    {
                      "items": [
                          {
                            "name": "mc do",
                            "type": "fast food"
                          },
                          {
                            "name": "sushi shop",
                            "type": "asiatique"
                          },
                          {
                            "name": "kfc",
                            "type": "fast food"
                          }
                      ]
                    }
                """.trimIndent(),
            RestaurantsResponse::class.java
        ).items

    fun getDistanceBetweenRestaurantAndUser(restaurantName: String, userLocation: UserLocation) =
        mapper.readValue(
            """
                    {
                      "result": 10
                    }
                """.trimIndent(),
            DistanceBetweenRestaurantAndMeResponse::class.java
        )
}

data class RestaurantsResponse(
    val items: List<RestaurantResponse>
) {
    data class RestaurantResponse(
        val name: String,
        val type: String,
    )
}

data class DistanceBetweenRestaurantAndMeResponse(
    val result: Int
)