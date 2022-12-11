package trying.cosmos.domain.user.service

import org.mindrot.jbcrypt.BCrypt
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import trying.cosmos.domain.user.dto.request.UserSearchCondition
import trying.cosmos.domain.user.dto.response.UserFindResponse
import trying.cosmos.domain.user.dto.response.UserInfoResponse
import trying.cosmos.domain.user.dto.response.UserListFindResponse
import trying.cosmos.domain.user.dto.response.UserSettingResponse
import trying.cosmos.domain.user.entity.EmailUser
import trying.cosmos.domain.user.entity.SocialUser
import trying.cosmos.domain.user.repository.UserRepository

@Service
@Transactional(readOnly = true)
class UserService(

    private val userRepository: UserRepository,

    private val sessionService: SessionService

) {

    @Transactional
    fun logout(sessionId: String) = sessionService.delete(sessionId)

    @Transactional
    fun withdraw(userId: Long) {
        sessionService.deleteByUserId(userId)
        userRepository.deleteById(userId)
    }

    fun find(userId: Long): UserFindResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw RuntimeException("User not exist")
        return UserFindResponse(user.id, user.name)
    }

    fun findList(condition: UserSearchCondition, pageable: Pageable): UserListFindResponse {
        val userSlice = userRepository.findAllBy(condition, pageable)
        return UserListFindResponse(userSlice.content.map { UserFindResponse(it.id, it.name) }, userSlice.size, userSlice.hasNext())
    }

    fun findInfo(userId: Long): UserInfoResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw RuntimeException("User not exist")
        return UserInfoResponse(
            id = user.id,
            name = user.name,
            loginBy = (user as? SocialUser)?.socialType?.toString() ?: "EMAIL",
            email = (user as? EmailUser)?.email
        )
    }

    @Transactional
    fun update(userId: Long, name: String?, password: String?) {
        val user = userRepository.findByIdOrNull(userId) ?: throw RuntimeException("User not exist")
        if (name != null) user.name = name
        if (user is EmailUser) {
            if (password != null) user.password = BCrypt.hashpw(password, BCrypt.gensalt())
        }
    }

    fun findSetting(userId: Long): UserSettingResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw RuntimeException("User not exist")
        return UserSettingResponse(
            enableGlobalNotification = user.setting.enableGlobalNotification,
            enableCourseNotification = user.setting.enableCourseNotification,
            enableReviewNotification = user.setting.enableReviewNotification
        )
    }

    @Transactional
    fun updateSetting(
        userId: Long,
        enableGlobalNotification: Boolean,
        enableCourseNotification: Boolean,
        enableReviewNotification: Boolean
    ) {
        val user = userRepository.findByIdOrNull(userId) ?: throw RuntimeException("User not exist")
        user.setting.enableGlobalNotification = enableGlobalNotification
        user.setting.enableCourseNotification = enableCourseNotification
        user.setting.enableReviewNotification = enableReviewNotification
    }
}