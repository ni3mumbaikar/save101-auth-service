package ind.ni3mumbaikar.microservices.authservice.entity

import jakarta.persistence.*

@Table(name = "all_users")
@Entity
class UserCredential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id = 0
    var name: String? = null
    var email: String? = null
    var password: String? = null
}