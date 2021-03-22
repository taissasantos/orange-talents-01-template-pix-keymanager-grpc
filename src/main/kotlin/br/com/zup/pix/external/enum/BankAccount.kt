package br.com.zup.pix.external.enum

data class BankAccount (
        val participant: String,
        val branch: String,
        val accountNumber: String,
        val accountType: AccountType
)