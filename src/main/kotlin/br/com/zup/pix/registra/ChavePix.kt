package br.com.zup.pix.registra

//import br.com.zup.TipoDeChave

import java.time.LocalDateTime
import javax.persistence.EnumType.STRING
import java.util.*
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
data class ChavePix(
        @field:NotNull
        @Column(nullable = false)
        var clientId: UUID?,

        @Enumerated(STRING)
        @Column(nullable = false)
        val tipo: TipoChave,

        @field:NotBlank
        @field:Size(max = 77)
        @Column(nullable = false, unique = true)
        var chave: String,

        @field:NotNull
        @Enumerated(STRING)
        @Column(nullable = false)
        val tipoDeConta: TipoConta,

        @field:Valid
        @Embedded
        val conta: ContaAssociada,


        val registradaEm: LocalDateTime = LocalDateTime.now()

) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null

    fun atualiza(key: String) {
        this.chave = key
    }


}
