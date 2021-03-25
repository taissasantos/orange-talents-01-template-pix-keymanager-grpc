package br.com.zup.pix.consulta

import br.com.zup.pix.external.BancoCentralClient
import br.com.zup.pix.remove.exception.ChaveNaoEncontradaException
import br.com.zup.pix.repository.ChavePixRespository
import br.com.zup.shared.ValidUUID
import io.micronaut.core.annotation.Introspected
import io.micronaut.http.HttpStatus
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
sealed class Filtro {

    abstract fun filtra(respository: ChavePixRespository, bancoCentralClient: BancoCentralClient): ChavePixResponse

    @Introspected
    data class PorPixId(
            @field:NotBlank @field:ValidUUID val clientId: String,
            @field:NotBlank @field:ValidUUID val pixId: String
    ): Filtro(){

        fun pixIdAsUuid() = UUID.fromString(pixId)
        fun clientIdAsUuid() = UUID.fromString(clientId)

        override fun filtra(respository: ChavePixRespository, bancoCentralClient: BancoCentralClient): ChavePixResponse {
            return respository.findByIdAndClientId(pixIdAsUuid(),clientIdAsUuid())
                    .map { ChavePixResponse.from(it) }
                    .orElseThrow{throw ChaveNaoEncontradaException("Não existe a chave")}
        }
    }


    @Introspected
    data class PorChave(@field:NotBlank @Size(max = 77) val chave: String): Filtro() {
        override fun filtra(respository: ChavePixRespository, bancoCentralClient: BancoCentralClient): ChavePixResponse {
           return respository.findByChave(chave)
                   .map { ChavePixResponse.from(it) }
                   .orElseGet {
                       val response = bancoCentralClient.buscaPorChave(chave)
                       when(response.status){
                           HttpStatus.OK -> response.body()?.toPix()
                           else -> throw ChaveNaoEncontradaException("Chave não encontrada")
                       }
                   }

        }
    }

    @Introspected
    class Invalido(): Filtro(){
        override fun filtra(respository: ChavePixRespository, bancoCentralClient: BancoCentralClient): ChavePixResponse {
            throw IllegalAccessException("Chave Pix Inválida")
        }
    }
}