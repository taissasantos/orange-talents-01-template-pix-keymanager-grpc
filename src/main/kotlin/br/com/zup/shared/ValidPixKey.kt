package br.com.zup.shared
import br.com.zup.pix.registra.NovaChavePix
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Singleton
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.annotation.AnnotationTarget.*
import io.micronaut.core.annotation.AnnotationValue
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.reflect.KClass


@MustBeDocumented
@Target(CLASS, TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = [ValidPixValidator::class])
annotation class ValidPixKey(
        val message: String = "A Chave pix é inválida.",
        val groups: Array<KClass<Any>> = [],
        val payload: Array<KClass<Payload>> = []
)

@Singleton
class ValidPixValidator: ConstraintValidator<ValidPixKey, NovaChavePix>{

    override fun isValid(
            value: NovaChavePix?,
            annotationMetadata: AnnotationValue<ValidPixKey>,
            context: ConstraintValidatorContext): Boolean {

        if( value?.tipo == null){
            return false
        }

        return value.tipo.validaChave(value.chave)
    }


}
