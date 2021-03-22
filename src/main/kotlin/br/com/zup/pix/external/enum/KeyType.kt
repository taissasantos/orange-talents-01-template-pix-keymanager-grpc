package br.com.zup.pix.external.enum

import br.com.zup.pix.registra.TipoChave

enum class KeyType {

    CPF,
    PHONE,
    EMAIL,
    RANDOM;

    companion object {
        fun by(domainType: TipoChave): KeyType {
            return when (domainType) {
                TipoChave.CPF -> CPF
                TipoChave.CELULAR -> PHONE
                TipoChave.EMAIL -> EMAIL
                TipoChave.ALEATORIA -> RANDOM
            }
        }
    }
}