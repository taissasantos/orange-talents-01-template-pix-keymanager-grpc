package br.com.zup.pix.remove

import br.com.zup.pix.external.BancoCentralClient
import br.com.zup.pix.external.DeletePixKeyRequest
import br.com.zup.pix.remove.exception.ChaveNaoEncontradaException
import br.com.zup.pix.repository.ChavePixRespository
import br.com.zup.shared.ValidUUID
import io.micronaut.http.HttpStatus
import io.micronaut.validation.Validated
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.validation.constraints.NotBlank

@Validated
@Singleton
class RemoveChavePixService(@Inject val repository: ChavePixRespository,
                            @Inject val bcbClient: BancoCentralClient) {

    @Transactional
    fun remove(
            @NotBlank @ValidUUID(message = "cliente ID com o formato inválido") clienteId: String?,
            @NotBlank @ValidUUID(message = "pix ID com o formato inválido") pixId: String?
    ){

        val uuidPixId = UUID.fromString(pixId)
        val uuidClientId = UUID.fromString(clienteId)

        val chave = repository.findByIdAndClientId(uuidPixId, uuidClientId)
                .orElseThrow{ ChaveNaoEncontradaException("Chave Pix não encontrada") }

        repository.deleteById(uuidPixId)

        val request = DeletePixKeyRequest(chave.chave, chave.conta.ispb)

        val bcbResponse = bcbClient.deleta(key = chave.chave, request = request)
        if(bcbResponse.status != HttpStatus.OK){
            throw IllegalStateException("Erro ao tentar excluir a chave no banco central")
        }

    }

}
