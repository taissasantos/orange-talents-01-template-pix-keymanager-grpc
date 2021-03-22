package br.com.zup.pix.registra

import javax.persistence.Embeddable
import javax.persistence.Embedded

@Embeddable
class ContaAssociada(val instituicao: String,
                     val ispb: String,
                     val nomeTitular: String,
                     val cpfTitular: String,
                     val agencia: String,
                     val numeroDaConta: String) {

}
