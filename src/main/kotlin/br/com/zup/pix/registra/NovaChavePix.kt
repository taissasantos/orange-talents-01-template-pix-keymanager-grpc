package br.com.zup.pix.registra


//import br.com.zup.TipoDeChave
import br.com.zup.TipoDeConta
import br.com.zup.shared.TipoChave

import br.com.zup.shared.ValidPixKey
import br.com.zup.shared.ValidUUID
import io.micronaut.core.annotation.Introspected
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@ValidPixKey
@Introspected
data class NovaChavePix(@ValidUUID @field:NotBlank val clientId: String?,
                        @field:NotNull val tipo: TipoChave?,
                        @field:Size(max = 77) val chave: String,
                        @field:NotNull val tipoDeConta: TipoDeConta?) {

    fun toModel(conta: ContaAssociada): ChavePix{
        return ChavePix(
                clientId = UUID.fromString(this.clientId),
                tipo = TipoChave.valueOf(this.tipo!!.name),
                chave = if(this.tipo == TipoChave.ALEATORIA) UUID.randomUUID().toString() else this.chave!!,
                tipoDeConta = TipoDeConta.valueOf(this.tipoDeConta!!.name),
                conta = conta

        )
    }
}