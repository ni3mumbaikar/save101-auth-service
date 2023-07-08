package ind.ni3mumbaikar.microservices.authservice.dto

import ind.ni3mumbaikar.microservices.authservice.entity.User

data class UserRegisterDto(
    var password: String,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val phone: String?
) {
    fun toUser(): User {
        val user = User()
        user.firstName = this.firstName
        user.lastName = this.lastName
        user.email = this.email
        user.phone = this.phone
        return user
    }

    override fun toString(): String {
        return super.toString()
    }
}

