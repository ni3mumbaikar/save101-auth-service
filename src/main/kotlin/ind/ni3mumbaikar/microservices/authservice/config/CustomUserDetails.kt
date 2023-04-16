package ind.ni3mumbaikar.microservices.authservice.config

import ind.ni3mumbaikar.microservices.authservice.entity.UserCredential
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class CustomUserDetails(userCredential: UserCredential) : UserDetails {
    private var username: String? = null
    private var password: String? = null

    init {
        this.password = userCredential.password
        this.username = userCredential.name
    }

    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return null
    }

    override fun getPassword(): String? {
        return this.password
    }

    override fun getUsername(): String? {
        return this.username
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