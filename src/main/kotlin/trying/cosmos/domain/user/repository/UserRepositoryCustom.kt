package trying.cosmos.domain.user.repository

interface UserRepositoryCustom {

    fun existsByEmail(email: String): Boolean

    fun existsByIdentifier(email: String): Boolean
}
