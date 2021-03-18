package br.com.zup.shared

import java.lang.annotation.Documented
import javax.validation.Constraint
import kotlin.annotation.AnnotationRetention.RUNTIME
import org.hibernate.validator.constraints.br.CPF
import javax.validation.Payload
import kotlin.annotation.AnnotationTarget.*
import kotlin.reflect.KClass

@Documented
@Constraint(validatedBy = [])
@Target(FIELD)
@Retention(RUNTIME)
@CPF
annotation class CPFValidator(
        val message: String = "CPF inválido.",
        val groups: Array<KClass<Any>> = [],
        val payload: Array<KClass<Payload>> = []
)
