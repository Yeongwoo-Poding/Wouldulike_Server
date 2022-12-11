package trying.cosmos.domain.user.controller

import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import trying.cosmos.domain.user.dto.*
import trying.cosmos.domain.user.dto.request.UserNameRequest
import trying.cosmos.domain.user.dto.request.UserPasswordRequest
import trying.cosmos.domain.user.dto.request.UserSearchCondition
import trying.cosmos.domain.user.dto.request.UserSettingRequest
import trying.cosmos.domain.user.dto.response.UserFindResponse
import trying.cosmos.domain.user.dto.response.UserInfoResponse
import trying.cosmos.domain.user.dto.response.UserListFindResponse
import trying.cosmos.domain.user.dto.response.UserSettingResponse
import trying.cosmos.domain.user.entity.AuthorityType.USER
import trying.cosmos.domain.user.service.UserService
import trying.cosmos.global.authentication.AuthorityLimit
import trying.cosmos.global.authentication.CurrentUser

@RestController
@RequestMapping("/users")
class UserController(

    private val userService: UserService

) {

    @AuthorityLimit(USER)
    @DeleteMapping("/{userId}/logout")
    fun logout(@PathVariable userId: Long) {
        val sessionId = CurrentUser.getSessionId() ?: throw RuntimeException("Not Authenticated")
        if (!isCurrentUser(userId)) throw RuntimeException("No Permission")
        userService.logout(sessionId)
    }

    @AuthorityLimit(USER)
    @DeleteMapping("/{userId}")
    fun withdraw(@PathVariable userId: Long) {
        if (!isCurrentUser(userId)) throw RuntimeException("No Permission")
        userService.withdraw(userId)
    }

    @GetMapping("/{userId}")
    fun find(@PathVariable userId: Long): UserFindResponse = userService.find(userId)

    @GetMapping
    fun findAll(@ModelAttribute condition: UserSearchCondition, pageable: Pageable): UserListFindResponse = userService.findList(condition, pageable)

    @AuthorityLimit(USER)
    @GetMapping("/me")
    fun findMe(): UserInfoResponse = userService.findInfo(CurrentUser.getUserId() ?: throw RuntimeException("Not Authenticated"))

    @AuthorityLimit(USER)
    @PutMapping("/{userId}/name")
    fun updateName(@PathVariable userId: Long, @RequestBody request: UserNameRequest) {
        if (!isCurrentUser(userId)) throw RuntimeException("No Permission")
        userService.updateName(userId, request.name)
    }

    @AuthorityLimit(USER)
    @PutMapping("/{userId}/password")
    fun updatePassword(@PathVariable userId: Long, @RequestBody request: UserPasswordRequest) {
        if (!isCurrentUser(userId)) throw RuntimeException("No Permission")
        userService.updatePassword(userId, request.password)
    }

    @AuthorityLimit(USER)
    @GetMapping("/{userId}/setting")
    fun getSetting(@PathVariable userId: Long): UserSettingResponse {
        if (!isCurrentUser(userId)) throw RuntimeException("No Permission")
        return userService.findSetting(userId)
    }

    @AuthorityLimit(USER)
    @PutMapping("/{userId}/setting")
    fun updateSetting(@PathVariable userId: Long, @RequestBody request: UserSettingRequest) {
        if (!isCurrentUser(userId)) throw RuntimeException("No Permission")
        userService.updateSetting(
            userId,
            request.enableGlobalNotification,
            request.enableCourseNotification,
            request.enableReviewNotification
        )
    }

    private fun isCurrentUser(userId: Long): Boolean {
        val currentId = CurrentUser.getUserId() ?: return false
        return currentId == userId
    }
}