package br.com.zup.shared

import javax.validation.constraints.Pattern.Flag.CASE_INSENSITIVE
import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.ReportAsSingleViolation
import kotlin.annotation.AnnotationRetention.RUNTIME
import javax.validation.constraints.Pattern
import kotlin.annotation.AnnotationTarget.*
import kotlin.reflect.KClass

@ReportAsSingleViolation
@Constraint(validatedBy = [])
@Pattern(
        regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$",
        flags = [CASE_INSENSITIVE]
)
@Retention(RUNTIME)
@Target(FIELD, CONSTRUCTOR, PROPERTY, VALUE_PARAMETER)
annotation class ValidUUID(
        val message: String = "Formado do id é inválido.",
        val groups: Array<KClass<Any>> = [],
        val payload: Array<KClass<Payload>> = []
)
