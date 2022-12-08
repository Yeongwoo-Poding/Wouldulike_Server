package trying.cosmos.domain.user.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import trying.cosmos.domain.user.dto.SocialJoinRequest
import trying.cosmos.domain.user.dto.SocialLoginRequest
import trying.cosmos.domain.user.dto.UserLoginResponse
import trying.cosmos.domain.user.service.SocialAuthenticationService

@RestController
@RequestMapping("/auth/social")
class SocialAuthenticationController(

    private val authenticationService: SocialAuthenticationService

) {

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