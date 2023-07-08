package ind.ni3mumbaikar.microservices.authservice.config

import ind.ni3mumbaikar.microservices.authservice.entity.UserCredential
import ind.ni3mumbaikar.microservices.authservice.repository.UserCredentialRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomUserDetailsService : UserDetailsService {

    private var logger: Logger = LoggerFactory.getLogger(CustomUserDetails::class.java)

    @Autowired
    private lateinit var userCredentialRepository: UserCredentialRepository

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails? {
        val credential: UserCredential? = userCredentialRepository.findByEmail(email)
        if (credential != null) {
            return CustomUserDetails(credential)
        } else {
            throw UsernameNotFoundException("User  Not Found / Invalid User Name")
        }
    }
}