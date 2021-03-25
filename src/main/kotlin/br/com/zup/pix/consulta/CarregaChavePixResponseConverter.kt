package br.com.zup.pix.consulta

import br.com.zup.CarregaChavePixResponse
import br.com.zup.TipoDeChave
import br.com.zup.TipoDeConta
import com.google.protobuf.Timestamp
import java.time.ZoneId

class CarregaChavePixResponseConverter {

    fun converter(chavePix: ChavePixResponse): CarregaChavePixResponse{
        val instant = chavePix.registradaEm.atZone(ZoneId.of("UTC")).toInstant()

        return CarregaChavePixResponse.newBuilder()
                .setClientId(chavePix.IdCliente?.toString() ?: "")
                .setPixId(chavePix.IdPix?.toString() ?: "")
                .setChave(CarregaChavePixResponse.ChavePix
                        .newBuilder()
                        .setTipo(TipoDeChave.valueOf(chavePix.tipo.name))
                        .setChave(chavePix.chave)
                        .setConta(CarregaChavePixResponse.ChavePix.ContaInfo.newBuilder()
                                .setTipo(TipoDeConta.valueOf(chavePix.tipo.name))
                                .setInstituicao(chavePix.conta.instituicao)
                                .setNomeDoTitular(chavePix.conta.nomeTitular)
                                .setCpfDoTitular(chavePix.conta.cpfTitular)
                                .setAgencia(chavePix.conta.agencia)
                                .setNumeroDaConta(chavePix.conta.numeroDaConta)
                                .build()
                        ).setCriadaEm(chavePix.registradaEm.let { val instant = chavePix.registradaEm.atZone(ZoneId.of("UTC")).toInstant()
                        Timestamp.newBuilder()
                                .setSeconds(instant.epochSecond)
                                .setNanos(instant.nano)
                                .build()})).build()

    }
}