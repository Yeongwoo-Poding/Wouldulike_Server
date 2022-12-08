package trying.cosmos.domain.user.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import trying.cosmos.domain.user.entity.QEmailUser.emailUser
import trying.cosmos.domain.user.entity.QSocialUser.socialUser

class UserRepositoryImpl(

    private val queryFactory: JPAQueryFactory

): UserRepositoryCustom {

    override fun existsByEmail(email: String): Boolean {
        val user = queryFactory.selectFrom(emailUser)
            .where(
                emailUser.email.eq(email)
            )
            .fetchOne()

        return user == null
    }

    override fun existsByIdentifier(identifier: String): Boolean {
        val user = queryFactory.selectFrom(socialUser)
            .where(
                socialUser.identifier.eq(identifier)
            )
            .fetchOne()

        return user == null
    }
}