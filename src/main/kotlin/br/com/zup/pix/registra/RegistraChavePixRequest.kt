package br.com.zup.pix.registra

import br.com.zup.RegistraChavePixRequest
import br.com.zup.TipoDeChave
import br.com.zup.TipoDeConta
import br.com.zup.shared.TipoChave

fun RegistraChavePixRequest.toModel(): NovaChavePix{
    return NovaChavePix(
        clientId = clientId,
        tipo = when(tipoDeChave){
            TipoDeChave.UNKNOWN_TIPO_CHAVE-> null
            else -> TipoChave.valueOf(tipoDeChave.name)
        },
        chave = chave,
        tipoDeConta = when(tipoDeConta){
            TipoDeConta.UNKNOWN_TIPO_CONTA -> null
            else -> TipoDeConta.valueOf(tipoDeConta.name)
        }
    )
}