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
    fun updateName(userId: Long, name: String) {
        val user = userRepository.findByIdOrNull(userId) ?: throw RuntimeException("User not exist")
        user.name = name
    }

    @Transactional
    fun updatePassword(userId: Long, password: String) {
        val user = userRepository.findByIdOrNull(userId) ?: throw RuntimeException("User not exist")
        (user as? EmailUser)?.password = BCrypt.hashpw(password, BCrypt.gensalt()) ?: throw RuntimeException("This user is Social user")
    }

    fun findSetting(userId: Long): UserSettingResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw RuntimeException("User not exist")
        val setting = userRepository.findSetting(user) ?: throw RuntimeException("User not exist")
        return UserSettingResponse(
            enableGlobalNotification = setting.enableGlobalNotification,
            enableCourseNotification = setting.enableCourseNotification,
            enableReviewNotification = setting.enableReviewNotification
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
        val setting = userRepository.findSetting(user) ?: throw RuntimeException("User not exist")
        setting.enableGlobalNotification = enableGlobalNotification
        setting.enableCourseNotification = enableCourseNotification
        setting.enableReviewNotification = enableReviewNotification
    }
}