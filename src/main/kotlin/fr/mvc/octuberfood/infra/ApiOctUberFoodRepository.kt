package fr.mvc.octuberfood.infra

import fr.mvc.octuberfood.domain.OctUberFoodRepository
import fr.mvc.octuberfood.domain.Restaurant
import fr.mvc.octuberfood.domain.users.UserLocation
import org.springframework.stereotype.Repository

@Repository
class ApiOctUberFoodRepository(
    private val apiRestaurantClient: ApiRestaurantClient
) : OctUberFoodRepository {

    override fun getListOfRestaurant(
        userLocation: UserLocation,
        userTypeRestaurantPreference: String
    ): List<Restaurant> {
        val restaurantsNearMe = apiRestaurantClient.getRestaurants(userLocation)
        val bestRatedRestaurantsNearMe = apiRestaurantClient.getBestRatedRestaurants(userLocation)
        return (restaurantsNearMe + bestRatedRestaurantsNearMe)
            .filter { it.type == userTypeRestaurantPreference }
            .map {
                val distanceWithMe =
                    apiRestaurantClient.getDistanceBetweenRestaurantAndUser(it.name, userLocation)
                Restaurant(
                    name = it.name,
                    type = it.type,
                    distanceWithMe = distanceWithMe.result
                )

            }
    }
}