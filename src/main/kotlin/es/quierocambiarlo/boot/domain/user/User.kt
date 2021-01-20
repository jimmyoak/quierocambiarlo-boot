package es.quierocambiarlo.boot.domain.user

import java.util.UUID

sealed class User {
    abstract val id: UserId
    abstract val name: Username
    abstract val email: Email
    abstract val phone: UserPhone?
}

data class Guest(
    override val id: UserId,
    override val name: Username,
    override val email: Email,
    override val phone: UserPhone?
) : User()

data class UserId(val value: UUID)
data class Username(val value: String)
data class Email(val value: String)
data class UserPhone(val value: String)
