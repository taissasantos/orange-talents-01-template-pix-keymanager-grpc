package br.com.zup.pix.external.response

import java.time.LocalDateTime

class DeletePixKeyResponse(
        val key: String,
        val participant: String,
        val deletedAt: LocalDateTime
)