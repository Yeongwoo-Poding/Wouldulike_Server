package trying.cosmos.domain.common.annotation

import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [PasswordPatternValidator::class])
annotation class Password(
    val message: String = "{validation.password.message}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)


class PasswordPatternValidator: ConstraintValidator<Password, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) return false
        val pattern = Regex("^[A-Za-z0-9!@#\$%^&*]{8,16}")
        return pattern.matches(value)
    }
}