package ind.ni3mumbaikar.microservices.authservice.repository

import ind.ni3mumbaikar.microservices.authservice.entity.UserCredential
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserCredentialRepository : CrudRepository<UserCredential, Number> {

    @Query("select * from USER_CREDENTIALS where USER_ID = (select user_id from users where email =:email)")
    fun findByEmail(@Param("email") email: String?): UserCredential?
}