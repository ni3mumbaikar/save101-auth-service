package ind.ni3mumbaikar.microservices.authservice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table


@Table(name = "USER_CREDENTIALS")
class UserCredential {
    @Id
    var credId: Number? = null
    var userId: Number? = null
    var password: String? = null
}