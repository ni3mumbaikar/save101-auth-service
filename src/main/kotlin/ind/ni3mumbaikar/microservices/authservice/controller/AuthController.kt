package ind.ni3mumbaikar.microservices.authservice.controller

import ind.ni3mumbaikar.microservices.authservice.dto.AuthRequest
import ind.ni3mumbaikar.microservices.authservice.dto.UserRegisterDto
import ind.ni3mumbaikar.microservices.authservice.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/auth")
class AuthController(private var authService: AuthService) {

    @PostMapping("/login")
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
    fun saveUser(@RequestBody credential: UserRegisterDto): HttpStatus? {
        if (authService.saveUser(credential)) {
            return HttpStatus.OK
        }
        return HttpStatus.BAD_REQUEST
    }

}