package trying.cosmos.domain.user.controller

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import trying.cosmos.domain.user.dto.request.SocialJoinRequest
import trying.cosmos.domain.user.dto.request.SocialLoginRequest
import trying.cosmos.domain.user.dto.response.UserLoginResponse
import trying.cosmos.domain.user.entity.SocialType
import trying.cosmos.domain.user.service.SocialUserService

@RestController
@RequestMapping("/auth/{type}")
class SocialUserController(

    private val authenticationService: SocialUserService

) {

    @PostMapping
    fun join(@PathVariable type: String, @Validated @RequestBody request: SocialJoinRequest): UserLoginResponse = authenticationService.join(
        request.name,
        SocialType.valueOf(type.uppercase()),
        request.identifier,
        request.pushToken
    )

    @PostMapping("/login")
    fun login(@PathVariable type: String, @Validated @RequestBody request: SocialLoginRequest): UserLoginResponse = authenticationService.login(
        SocialType.valueOf(type.uppercase()),
        request.identifier,
        request.pushToken
    )
}