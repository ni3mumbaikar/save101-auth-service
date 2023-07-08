package ind.ni3mumbaikar.microservices.authservice.repository

import ind.ni3mumbaikar.microservices.authservice.entity.User
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Number> {
    @Query("select * from users where email = :email")
    fun findByEmail(@Param("email") email: String?): User?
}