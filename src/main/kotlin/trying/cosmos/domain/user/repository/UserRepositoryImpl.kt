package trying.cosmos.domain.user.repository

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import trying.cosmos.domain.user.dto.request.UserSearchCondition
import trying.cosmos.domain.user.entity.QEmailUser.emailUser
import trying.cosmos.domain.user.entity.QSocialUser.socialUser
import trying.cosmos.domain.user.entity.QUser.user
import trying.cosmos.domain.user.entity.User
import trying.cosmos.global.extension.toSlice

class UserRepositoryImpl(

    private val queryFactory: JPAQueryFactory

): UserRepositoryCustom {

    override fun existsByEmail(email: String): Boolean {
        val user = queryFactory.selectFrom(emailUser)
            .where(
                emailUser.email.eq(email)
            )
            .fetchOne()

        return user != null
    }

    override fun existsByIdentifier(identifier: String): Boolean {
        val user = queryFactory.selectFrom(socialUser)
            .where(
                socialUser.identifier.eq(identifier)
            )
            .fetchOne()

        return user != null
    }

    override fun findAllBy(condition: UserSearchCondition, pageable: Pageable): Slice<User> {
        val contents = queryFactory.selectFrom(user)
            .where(
                nameCondition(condition.name)
            )
            .offset(pageable.offset)
            .limit(pageable.pageSize + 1L)
            .fetch()

        return contents.toSlice(pageable)
    }

    private fun nameCondition(name: String?): BooleanExpression? {
        return user.name.eq(name ?: return null)
    }
}