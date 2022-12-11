package trying.cosmos.global.authentication

import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import trying.cosmos.domain.user.service.SessionService
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationInterceptor(

    private val sessionService: SessionService,

    private val tokenProvider: TokenProvider

): HandlerInterceptor {

    private val accessTokenHeader = "accessToken"

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val token = request.getHeader(accessTokenHeader)
        val authorityLevel = authenticate(token)

        val annotation = (handler as? HandlerMethod)?.getMethodAnnotation(AuthorityLimit::class.java)
        val authorityLimit = annotation?.value?.level ?: 0

        if (authorityLevel < authorityLimit) throw RuntimeException("No permission")
        return true
    }

    private fun authenticate(token: String?): Int {
        if (!tokenProvider.isValid(token)) return 0

        val sessionId = tokenProvider.getSubject(token)
        CurrentUser.setSessionId(sessionId)

        val session = if (sessionId == null) null else sessionService.find(sessionId)
        val userId = session?.userId
        CurrentUser.setUserId(userId)

        return session?.authorityType?.level ?: 0
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        CurrentUser.free()
    }
}