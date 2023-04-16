package ind.ni3mumbaikar.microservices.authservice.controller

import ind.ni3mumbaikar.microservices.authservice.dto.AuthRequest
import ind.ni3mumbaikar.microservices.authservice.entity.UserCredential
import ind.ni3mumbaikar.microservices.authservice.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/auth")
class AuthController(private var authService: AuthService) {

    @PostMapping("/token")
    fun getToken(@RequestBody authRequest: AuthRequest): String? {
        return authService.getToken(authRequest)
    }

    @GetMapping("/validate")
    fun validateToken(@RequestParam("token") token: String?): HttpStatus {
        return if (token != null) {
            authService.validateToken(token)
            HttpStatus.OK
        } else {
            HttpStatus.BAD_REQUEST
        }
    }

    @PostMapping("/register")
    fun saveUser(@RequestBody credential: UserCredential): String? {
        return authService.saveUser(credential)
    }

}