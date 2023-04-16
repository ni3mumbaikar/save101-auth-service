package ind.ni3mumbaikar.microservices.authservice.service

import ind.ni3mumbaikar.microservices.authservice.dto.AuthRequest
import ind.ni3mumbaikar.microservices.authservice.entity.UserCredential
import ind.ni3mumbaikar.microservices.authservice.repository.UserCredentialRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private var userCredentialRepository: UserCredentialRepository,
    private var jwtService: JwtService,
    private var authenticationManager: AuthenticationManager,
    private var passwordEncoder: PasswordEncoder
) {

    private var logger: Logger = LoggerFactory.getLogger(AuthService::class.java)

    fun getToken(authRequest: AuthRequest): String {
        print(authRequest.password)
        val authenticate: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.username,
                authRequest.password
            )
        )
        return if (authenticate.isAuthenticated) {
            logger.debug("Authentication Successful for ${authRequest.username}")
            generateToken(authRequest.username)
        } else {
            logger.error("Invalid access by ${authRequest.username}")
            "Invalid Access"
        }
    }

    fun generateToken(username: String): String {
        return jwtService.generateToken(username)
    }

    fun validateToken(token: String) {
        jwtService.validateToken(token)
    }

    fun saveUser(credential: UserCredential): String {
        credential.password = passwordEncoder.encode(credential.password)
        userCredentialRepository.save(credential)
        return "New User Created to Save101"
    }

}