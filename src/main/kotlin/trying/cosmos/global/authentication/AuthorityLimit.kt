package trying.cosmos.global.authentication

import trying.cosmos.domain.user.entity.AuthorityType

@Target(allowedTargets = [AnnotationTarget.FUNCTION])
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthorityLimit(

    val value: AuthorityType

)
