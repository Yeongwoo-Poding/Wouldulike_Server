package trying.cosmos.domain.user.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import trying.cosmos.domain.user.dto.request.UserSearchCondition
import trying.cosmos.domain.user.entity.User

interface UserRepositoryCustom {

    fun existsByEmail(email: String): Boolean

    fun existsByIdentifier(identifier: String): Boolean

    fun findAllBy(condition: UserSearchCondition, pageable: Pageable): Slice<User>
}
