package trying.cosmos.domain.user.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import trying.cosmos.domain.user.dto.request.UserSearchCondition
import trying.cosmos.domain.user.entity.EmailUser
import trying.cosmos.domain.user.entity.SocialUser
import trying.cosmos.domain.user.entity.User
import trying.cosmos.domain.user.entity.UserSetting

interface UserRepository: JpaRepository<User, Long>, UserRepositoryCustom {

    @Query("SELECT u from EmailUser u where u.email = :email")
    fun findByEmail(email: String): EmailUser?

    @Query("SELECT u from SocialUser u where u.identifier = :identifier")
    fun findByIdentifier(identifier: String): SocialUser?

    fun findAllBy(condition: UserSearchCondition, pageable: Pageable): Slice<User>

    @Query("SELECT us from UserSetting us where us.user = :user")
    fun findSetting(user: User): UserSetting?
}