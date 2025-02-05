package br.com.zup.pix.registra


import br.com.zup.pix.external.BancoCentralClient
import br.com.zup.pix.external.CreatePixKeyRequest
import br.com.zup.pix.external.DadosClienteClient
import br.com.zup.pix.repository.ChavePixRespository
import br.com.zup.shared.ChavePixExistenteException
import io.micronaut.http.HttpStatus
import io.micronaut.validation.Validated
import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Singleton
class NovaChavePixService(@Inject val repository: ChavePixRespository,
                          @Inject val itauClient: DadosClienteClient,
                          @Inject val bcbClient: BancoCentralClient) {

    @Transactional
    fun registra(@Valid chaveNova: NovaChavePix): ChavePix{
        if(repository.existsByChave(chaveNova.chave))
           throw ChavePixExistenteException("chave informada já existe")

        val response = itauClient.buscaDadosConta(chaveNova.clientId!!, chaveNova.tipoDeConta!!.name)
        val conta = response.body()?.toModel() ?: throw IllegalAccessException("Cliente não localizado no Itau")

        val chave = chaveNova.toModel(conta)

        repository.save(chave)

        val bcbRequest = CreatePixKeyRequest.toRequest(pix = chave)
        val bcbResponse = bcbClient.cadastra(bcbRequest)
        if(bcbResponse.status != HttpStatus.CREATED){
            throw IllegalStateException("Erro ao tentar cadastrar chave no banco central")
        }

        chave.atualiza(bcbResponse.body()!!.key)

        return chave


    }

}
