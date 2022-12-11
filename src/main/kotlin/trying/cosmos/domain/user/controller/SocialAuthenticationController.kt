package trying.cosmos.domain.user.controller

import org.springframework.web.bind.annotation.*
import trying.cosmos.domain.user.dto.request.SocialJoinRequest
import trying.cosmos.domain.user.dto.request.SocialLoginRequest
import trying.cosmos.domain.user.dto.response.UserLoginResponse
import trying.cosmos.domain.user.entity.SocialType
import trying.cosmos.domain.user.service.SocialAuthenticationService

@RestController
@RequestMapping("/auth/{type}")
class SocialAuthenticationController(

    private val authenticationService: SocialAuthenticationService

) {

    @PostMapping
    fun join(@PathVariable type: String, @RequestBody request: SocialJoinRequest): UserLoginResponse = authenticationService.join(
        request.name,
        SocialType.valueOf(type.uppercase()),
        request.identifier,
        request.pushToken
    )

    @PostMapping("/login")
    fun login(@PathVariable type: String, @RequestBody request: SocialLoginRequest): UserLoginResponse = authenticationService.login(
        SocialType.valueOf(type.uppercase()),
        request.identifier,
        request.pushToken
    )
}