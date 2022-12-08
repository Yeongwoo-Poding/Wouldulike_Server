package trying.cosmos.global.authentication

object CurrentUser {

    private val currentUserId: ThreadLocal<Long?> = ThreadLocal()
    private val currentSessionId: ThreadLocal<String?> = ThreadLocal()

    fun getUserId() = currentUserId.get()

    fun setUserId(userId: Long?) = currentUserId.set(userId)

    fun getSessionId() = currentSessionId.get()

    fun setSessionId(sessionId: String?) = currentSessionId.set(sessionId)

    fun free() {
        currentUserId.remove()
        currentSessionId.remove()
    }
}