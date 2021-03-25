package br.com.zup.pix.consulta


import br.com.zup.pix.registra.ChavePix
import br.com.zup.pix.registra.ContaAssociada
import br.com.zup.pix.registra.TipoChave
import java.time.LocalDateTime
import java.util.*

data class ChavePixResponse(
        val IdPix: UUID? = null,
        val IdCliente: UUID? = null,
        val tipo: TipoChave,
        val chave: String,
        val conta: ContaAssociada,
        val registradaEm: LocalDateTime,
        val clientId: Nothing?,
        val tipoDeConta: Any
) {

    companion object {
        fun from(chave: ChavePix): ChavePixResponse {
            return ChavePixResponse(
                    IdPix = chave.id,
                    IdCliente = chave.clientId,
                    tipo = chave.tipo,
                    chave = chave.chave,
                    conta = chave.conta,
                    registradaEm = chave.registradaEm,
                    clientId = null,
                    tipoDeConta = chave.conta
            )
        }
    }
}
