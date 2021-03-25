package br.com.zup.pix.registra

import br.com.zup.pix.external.InstituicaoResponse
import javax.persistence.Embeddable

@Embeddable
class ContaAssociada(val instituicao: String,
                     val ispb: String,
                     val nomeTitular: String,
                     val cpfTitular: String,
                     val agencia: String,
                     val numeroDaConta: String) {

}
