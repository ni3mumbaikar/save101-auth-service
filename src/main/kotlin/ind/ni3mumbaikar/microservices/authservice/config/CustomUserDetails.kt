package ind.ni3mumbaikar.microservices.authservice.config

import ind.ni3mumbaikar.microservices.authservice.entity.UserCredential
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class CustomUserDetails(userCredential: UserCredential) : UserDetails {
    private var userId: String? = null
    private var password: String? = null

    init {
        this.password = userCredential.password
        this.userId = userCredential.userId.toString()
    }

    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return null
    }

    override fun getPassword(): String? {
        return this.password
    }

    override fun getUsername(): String? {
        return this.userId
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}