package fr.mvc.octuberfood.usecases

import fr.mvc.octuberfood.domain.OctUberFoodRepository
import fr.mvc.octuberfood.domain.Restaurant
import fr.mvc.octuberfood.domain.users.UserRepository
import org.springframework.stereotype.Service

@Service
class RestaurantNearMeUseCase(
    private val octUberFoodRepository: OctUberFoodRepository,
    private val userRepository: UserRepository
) {
    operator fun invoke(): List<Restaurant> {
        val userLocation = userRepository.getUserLocation()
        val restaurantPreference = userRepository.getUserRestaurantPreference()

        return octUberFoodRepository.getListOfRestaurant(
            userLocation = userLocation,
            userTypeRestaurantPreference = restaurantPreference
        )
    }
}