package fr.mvc.octuberfood.domain

import fr.mvc.octuberfood.domain.users.UserLocation

interface OctUberFoodRepository {
    fun getListOfRestaurant(userLocation: UserLocation, userTypeRestaurantPreference: String): List<Restaurant>
}