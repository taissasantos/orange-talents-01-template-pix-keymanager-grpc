package br.com.zup.pix.external


import br.com.zup.pix.external.enum.AccountType
import br.com.zup.pix.external.enum.BankAccount
import br.com.zup.pix.external.enum.KeyType
import br.com.zup.pix.external.enum.Owner
import br.com.zup.pix.external.response.CreatePixKeyResponse
import br.com.zup.pix.external.response.DeletePixKeyResponse
import br.com.zup.pix.registra.ChavePix
import br.com.zup.pix.registra.ContaAssociada
import br.com.zup.pix.registra.TipoChave
import br.com.zup.pix.registra.TipoConta
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client
import java.time.LocalDateTime
import javax.validation.Valid

@Client(value = "http://localhost:8082")
interface BancoCentralClient {

    @Post(
            value = "/api/v1/pix/keys",
            produces = [MediaType.APPLICATION_XML]
            )
    fun cadastra(@Body request: CreatePixKeyRequest): HttpResponse<CreatePixKeyResponse>

    @Delete(
            value = "api/v1/pix/keys/{key}",
            produces = [MediaType.APPLICATION_XML],
            consumes = [MediaType.APPLICATION_XML]
    )
    fun deleta(@PathVariable key: String, @Body request: DeletePixKeyRequest): HttpResponse<DeletePixKeyResponse>

    @Get(
            value = "api/v1/pix/keys/{key}",
            consumes = [MediaType.APPLICATION_XML]
    )
    fun buscaPorChave(@PathVariable key: String): HttpResponse<PixKeyDetailsResponse>
}

data class CreatePixKeyRequest(
        val keyType: KeyType,
        val key: String,
        val bankAccount: BankAccount,
        val owner: Owner
) {

    companion object {
        fun toRequest(pix: ChavePix): CreatePixKeyRequest {
            return CreatePixKeyRequest(
                    keyType = KeyType.by(pix.tipo),
                    key = pix.chave,
                    bankAccount = BankAccount(
                            participant = pix.conta.ispb,
                            branch = pix.conta.agencia,
                            accountNumber = pix.conta.numeroDaConta,
                            accountType = AccountType.by(pix.tipoDeConta)
                    ),
                    owner = Owner(
                            type = Owner.OwnerType.NATURAL_PERSON,
                            name = pix.conta.nomeTitular,
                            taxIdNumber = pix.conta.cpfTitular
                    )
            )
        }
    }
}

data class DeletePixKeyRequest(
        val key: String,
        val participant: String
)

data class PixKeyDetailsResponse(
        val keyType: KeyType,
        val key: String,
        val bankAccount: BankAccount,
        val owner: Owner,
        val createdAt: LocalDateTime
) {
    fun toPix(): ChavePix {

        return ChavePix(
                clientId = null,
                tipo = when (keyType) {
                    KeyType.CPF -> TipoChave.CPF
                    KeyType.PHONE -> TipoChave.CELULAR
                    KeyType.EMAIL -> TipoChave.EMAIL
                    KeyType.RANDOM -> TipoChave.ALEATORIA
                },
                chave = key,
                tipoDeConta = when (bankAccount.accountType) {
                    AccountType.CACC -> TipoConta.CONTA_CORRENTE
                    AccountType.SVGS -> TipoConta.CONTA_POUPANCA
                },
                conta = ContaAssociada(
                        instituicao = "",
                        ispb = bankAccount.participant,
                        agencia = bankAccount.branch,
                        numeroDaConta = bankAccount.accountNumber,
                        nomeTitular = owner.name,
                        cpfTitular = owner.taxIdNumber
                ),
                registradaEm = createdAt
        )
    }
}

