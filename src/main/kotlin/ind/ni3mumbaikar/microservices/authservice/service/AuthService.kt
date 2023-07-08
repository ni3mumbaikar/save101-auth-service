package ind.ni3mumbaikar.microservices.authservice.service

import ind.ni3mumbaikar.microservices.authservice.dto.AuthRequest
import ind.ni3mumbaikar.microservices.authservice.dto.UserRegisterDto
import ind.ni3mumbaikar.microservices.authservice.entity.User
import ind.ni3mumbaikar.microservices.authservice.entity.UserCredential
import ind.ni3mumbaikar.microservices.authservice.repository.UserCredentialRepository
import ind.ni3mumbaikar.microservices.authservice.repository.UserRepository
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
    private var userRepository: UserRepository,
    private var jwtService: JwtService,
    private var authenticationManager: AuthenticationManager,
    private var passwordEncoder: PasswordEncoder
) {

    private var logger: Logger = LoggerFactory.getLogger(AuthService::class.java)

    fun getToken(authRequest: AuthRequest): String {
        val authenticate: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.email,
                authRequest.password
            )
        )
        return if (authenticate.isAuthenticated) {
            logger.debug("Authentication Successful for ${authRequest.email}")
            generateToken(authRequest.email)
        } else {
            logger.error("Invalid access by ${authRequest.email}")
            "Invalid Access"
        }
    }

    fun generateToken(email: String): String {
        return jwtService.generateToken(email)
    }

    fun validateToken(token: String) {
        jwtService.validateToken(token)
    }

    fun saveUser(userRegisterDto: UserRegisterDto): Boolean {
        try {
            userRepository.save(userRegisterDto.toUser())
            val user: User? = userRepository.findByEmail(userRegisterDto.email)
            if (user != null) {
                val credential = UserCredential()
                credential.userId = user.userId
                credential.password = passwordEncoder.encode(userRegisterDto.password)
                userCredentialRepository.save(credential)
            }
        } catch (e: Exception) {
            logger.error(e.message)
            logger.error(e.stackTrace.toString())
        }
        return true
    }

}