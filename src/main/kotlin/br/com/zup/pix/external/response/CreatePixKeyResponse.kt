package br.com.zup.pix.external.response

import br.com.zup.pix.external.enum.BankAccount
import br.com.zup.pix.external.enum.KeyType
import br.com.zup.pix.external.enum.Owner
import java.time.LocalDateTime

data class CreatePixKeyResponse (
        val keyType: KeyType,
        val key: String,
        val bankAccount: BankAccount,
        val owner: Owner,
        val createdAt: LocalDateTime
)