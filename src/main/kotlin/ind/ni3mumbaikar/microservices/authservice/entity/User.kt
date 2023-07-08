package ind.ni3mumbaikar.microservices.authservice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("USERS")
class User {
    @Id
    var userId: Number? = null

    @Column("FNAME")
    var firstName: String? = null

    @Column("LNAME")
    var lastName: String? = null
    var email: String? = null
    var phone: String? = null
}