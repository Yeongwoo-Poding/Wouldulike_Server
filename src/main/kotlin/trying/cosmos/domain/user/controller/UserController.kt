package trying.cosmos.domain.user.controller

import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import trying.cosmos.domain.user.dto.*
import trying.cosmos.domain.user.service.UserService

@RestController
@RequestMapping("/users")
class UserController(

    private val userService: UserService

) {

    @DeleteMapping("/{userId}/logout")
    fun logout(@PathVariable userId: Long) {
        TODO("Need ThreadLocal to save current session")
    }

    @DeleteMapping("/{userId}")
    fun withdraw(@PathVariable userId: Long) = userService.withdraw(userId)

    @GetMapping("/{userId}")
    fun find(@PathVariable userId: Long): UserFindResponse = userService.find(userId)

    @GetMapping
    fun findAll(@RequestBody condition: UserSearchCondition, pageable: Pageable): UserListFindResponse = userService.findList(condition, pageable)

    @GetMapping("/me")
    fun findMe(): UserInfoResponse {
        TODO("Need ThreadLocal to save current user")
    }

    @PutMapping("/{userId}/name")
    fun updateName(@PathVariable userId: Long, @RequestBody request: UserNameRequest) = userService.updateName(userId, request.name)

    @PutMapping("/{userId}/password")
    fun updatePassword(@PathVariable userId: Long, @RequestBody request: UserPasswordRequest) = userService.updatePassword(userId, request.password)

    @GetMapping("/{userId}/setting")
    fun getSetting(@PathVariable userId: Long): UserSettingResponse = userService.findSetting(userId)

    @PutMapping("/{userId}/setting")
    fun updateSetting(@PathVariable userId: Long, @RequestBody request: UserSettingRequest) = userService.updateSetting(
        userId,
        request.enableGlobalNotification,
        request.enableCourseNotification,
        request.enableReviewNotification
    )
}