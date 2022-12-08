package trying.cosmos.domain.user.controller

import org.springframework.web.bind.annotation.*
import trying.cosmos.domain.user.dto.ResetPasswordRequest
import trying.cosmos.domain.user.dto.UserExistResponse
import trying.cosmos.domain.user.dto.UserLoginResponse
import trying.cosmos.domain.user.service.EmailAuthenticationService

@RestController
@RequestMapping("/auth/email")
class EmailAuthenticationController(

    private val authenticationService: EmailAuthenticationService

) {

    data class EmailJoinRequest(
        val email: String,
        val password: String,
        val name: String,
        val pushToken: String
    )

    data class EmailLoginRequest(
        val email: String,
        val password: String,
        val pushToken: String
    )

    @GetMapping("/exist")
    fun isExist(@RequestParam email: String): UserExistResponse = authenticationService.isExist(email)

    @PostMapping
    fun join(@RequestBody request: EmailJoinRequest): UserLoginResponse = authenticationService.join(
        request.name,
        request.email,
        request.password,
        request.pushToken
    )

    @PostMapping("/login")
    fun login(@RequestBody request: EmailLoginRequest): UserLoginResponse = authenticationService.login(
        request.email,
        request.password,
        request.pushToken
    )

    @PutMapping("/password")
    fun resetPassword(@RequestBody request: ResetPasswordRequest) = authenticationService.resetPassword(request.email)
}