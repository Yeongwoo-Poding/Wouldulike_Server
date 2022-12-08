package trying.cosmos.global.authentication

import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import trying.cosmos.domain.user.entity.AuthorityType
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
        val sessionId = tokenProvider.getSubject(token)
        CurrentUser.setSessionId(sessionId)
        val session = if (sessionId == null) null else sessionService.find(sessionId)
        val userId = session?.userId
        val authority = session?.authorityType

        val annotation = (handler as? HandlerMethod)?.getMethodAnnotation(AuthorityLimit::class.java)
        val limit = annotation?.value

        if (authority?.hasPermission(limit) ?: return false) throw RuntimeException("No permission")
        CurrentUser.setUserId(userId)

        return true
    }

    private fun AuthorityType.hasPermission(of: AuthorityType?): Boolean = this.level >= (of?.level ?: 0)

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        CurrentUser.free()
    }
}