package br.com.zup.shared

import io.micronaut.validation.validator.constraints.EmailValidator
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator

enum class TipoChave {

    CPF {
        override fun validaChave(chave: String?): Boolean {
            if (chave.isNullOrBlank() || !chave.matches("^[0-9]{11}\$".toRegex()))
                return false

            return CPFValidator().run {
                initialize(null)
                isValid(chave, null)
            }
        }
    },

    CELULAR {
        override fun validaChave(chave: String?): Boolean {
            return !chave.isNullOrBlank() || chave?.matches("^\\+[1-9][0-9]\\d{1,14}\$".toRegex()) ?: false
        }
    },

    EMAIL {
        override fun validaChave(chave: String?): Boolean {
            if (chave.isNullOrBlank())
                return false

            return EmailValidator().run {
                initialize(null)
                isValid(chave, null)
            }
        }
    },

    ALEATORIA {
        override fun validaChave(chave: String?) = chave.isNullOrBlank()

    };


    abstract fun validaChave(chave: String?): Boolean

}
