package fr.mvc.octuberfood.infra

import fr.mvc.octuberfood.domain.users.UserLocation
import fr.mvc.octuberfood.domain.users.UserRepository
import org.springframework.stereotype.Repository

@Repository
class Auth0UserRepository : UserRepository {

    override fun getUserLocation() = UserLocation(1, 2)
    override fun getUserRestaurantPreference(): String = "asiatique"
}