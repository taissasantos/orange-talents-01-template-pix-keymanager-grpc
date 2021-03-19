package br.com.zup.pix.remove.exception

import br.com.zup.shared.ExceptionHandler
import br.com.zup.shared.StatusWrapper
import io.grpc.Status
import javax.inject.Singleton

@Singleton
class ChaveNaoEncontradaExceptionHandler: ExceptionHandler<ChaveNaoEncontradaException> {

    override fun handle(e: ChaveNaoEncontradaException): StatusWrapper {
        return StatusWrapper(
                Status.NOT_FOUND
                        .withDescription(e.message)
                        .withCause(e)
        )
    }

    override fun supports(e: Exception): Boolean {
        return e is ChaveNaoEncontradaException
    }

}