package trying.cosmos.domain.user.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import trying.cosmos.domain.user.entity.EmailUser
import trying.cosmos.domain.user.entity.SocialUser
import trying.cosmos.domain.user.entity.User

interface UserRepository: JpaRepository<User, Long>, UserRepositoryCustom {

    @Query("SELECT u FROM EmailUser u WHERE u.email = :email")
    fun findByEmail(email: String): EmailUser?

    @Query("SELECT u FROM SocialUser u WHERE u.identifier = :identifier")
    fun findByIdentifier(identifier: String): SocialUser?
}