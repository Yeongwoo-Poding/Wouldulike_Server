package trying.cosmos.global.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import trying.cosmos.domain.user.service.SessionService
import trying.cosmos.global.authentication.AuthenticationInterceptor
import trying.cosmos.global.authentication.TokenProvider

@Configuration
class WebConfig(

    private val sessionService: SessionService,

    private val tokenProvider: TokenProvider

): WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(AuthenticationInterceptor(sessionService, tokenProvider))
    }
}