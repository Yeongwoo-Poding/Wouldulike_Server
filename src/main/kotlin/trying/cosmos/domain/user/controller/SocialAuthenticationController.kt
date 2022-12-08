package trying.cosmos.domain.user.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import trying.cosmos.domain.user.dto.UserLoginResponse
import trying.cosmos.domain.user.entity.SocialType
import trying.cosmos.domain.user.service.SocialAuthenticationService

@RestController
@RequestMapping("/auth/social")
class SocialAuthenticationController(

    private val authenticationService: SocialAuthenticationService

) {

    data class SocialJoinRequest(
        val socialType: SocialType,
        val identifier: String,
        val name: String,
        val pushToken: String
    )

    data class SocialLoginRequest(
        val socialType: SocialType,
        val identifier: String,
        val pushToken: String
    )

    @PostMapping
    fun join(@RequestBody request: SocialJoinRequest): UserLoginResponse = authenticationService.join(
        request.name,
        request.socialType,
        request.identifier,
        request.pushToken
    )

    @PostMapping("/login")
    fun login(@RequestBody request: SocialLoginRequest): UserLoginResponse = authenticationService.login(
        request.socialType,
        request.identifier,
        request.pushToken
    )
}