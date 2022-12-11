package trying.cosmos.domain.common.annotation

import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import javax.validation.constraints.Pattern
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Pattern(regexp = "^[가-힣A-Za-z0-9]{2,8}")
@Constraint(validatedBy = [UsernamePatternValidator::class])
annotation class Username(
    val message: String = "{validation.username.message}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)

class UsernamePatternValidator: ConstraintValidator<Username, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) return false
        val pattern = Regex("^[가-힣A-Za-z0-9]{2,8}")
        return pattern.matches(value)
    }
}