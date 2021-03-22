package br.com.zup.pix.external.enum

import br.com.zup.pix.registra.TipoConta


enum class AccountType {

    CACC,
    SVGS;

    companion object {
        fun by(domainType: TipoConta): AccountType {
            return when (domainType) {
                TipoConta.CONTA_CORRENTE -> CACC
                TipoConta.CONTA_POUPANCA -> SVGS
            }
        }
    }
}