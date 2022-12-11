package trying.cosmos.domain.user.controller

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import trying.cosmos.domain.user.dto.*
import trying.cosmos.domain.user.dto.request.EmailJoinRequest
import trying.cosmos.domain.user.dto.request.EmailLoginRequest
import trying.cosmos.domain.user.dto.request.ResetPasswordRequest
import trying.cosmos.domain.user.dto.response.UserExistResponse
import trying.cosmos.domain.user.dto.response.UserLoginResponse
import trying.cosmos.domain.user.service.EmailUserService

@RestController
@RequestMapping("/auth/email")
class EmailUserController(

    private val authenticationService: EmailUserService

) {

    @GetMapping("/exist")
    fun isExist(@RequestParam email: String): UserExistResponse = authenticationService.isExist(email)

    @PostMapping
    fun join(@Validated @RequestBody request: EmailJoinRequest): UserLoginResponse = authenticationService.join(
        request.name,
        request.email,
        request.password,
        request.pushToken
    )

    @PostMapping("/login")
    fun login(@Validated @RequestBody request: EmailLoginRequest): UserLoginResponse = authenticationService.login(
        request.email,
        request.password,
        request.pushToken
    )

    @PutMapping("/password")
    fun resetPassword(@Validated @RequestBody request: ResetPasswordRequest) = authenticationService.resetPassword(request.email)
}