package fr.mvc.octuberfood.domain.users

import fr.mvc.octuberfood.domain.users.UserLocation

interface UserRepository {
    fun getUserLocation(): UserLocation
    fun getUserRestaurantPreference(): String
}