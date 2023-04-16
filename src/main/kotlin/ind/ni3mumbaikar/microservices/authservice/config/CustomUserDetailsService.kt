package ind.ni3mumbaikar.microservices.authservice.config

import ind.ni3mumbaikar.microservices.authservice.entity.UserCredential
import ind.ni3mumbaikar.microservices.authservice.repository.UserCredentialRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomUserDetailsService : UserDetailsService {
    @Autowired
    private val repository: UserCredentialRepository? = null

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails? {
        val credential: UserCredential? = repository!!.findByName(username)
        if (credential != null) {
            return CustomUserDetails(credential)
        } else {
            throw Exception("Customer Not Found / Invalid Customer Name")
        }
    }
}