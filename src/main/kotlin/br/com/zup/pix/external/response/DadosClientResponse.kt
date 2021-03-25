package br.com.zup.pix.external

import br.com.zup.pix.registra.ContaAssociada

data class DadosClientResponse(
        val tipo: String,
        val instituicao: InstituicaoResponse,
        val agencia: String,
        val numero: String,
        val titular: TitularResponse
) {

    fun toModel(): ContaAssociada{
        return ContaAssociada(
                instituicao = this.instituicao.nome,
                ispb = this.instituicao.ispb,
                nomeTitular = this.titular.nome,
                cpfTitular = this.titular.cpf,
                agencia = this.agencia,
                numeroDaConta = this.numero
        )
    }

}

data class TitularResponse(val id: String, val nome: String, val cpf: String)

data class InstituicaoResponse(var nome: String, var ispb: String){

    fun toModel(): InstituicaoResponse{
        return InstituicaoResponse(
                nome = nome,
                ispb = ispb
        )
    }
}
