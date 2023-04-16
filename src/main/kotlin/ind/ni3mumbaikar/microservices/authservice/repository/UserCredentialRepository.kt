package ind.ni3mumbaikar.microservices.authservice.repository

import ind.ni3mumbaikar.microservices.authservice.entity.UserCredential
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserCredentialRepository : JpaRepository<UserCredential, Int> {
    fun findByName(username: String): UserCredential?
    fun findByEmail(email: String): UserCredential?
}