package trying.cosmos.domain.user.entity

enum class AuthorityType(

    val level: Int

) {
    USER(1), ADMIN(2)
}
