package br.com.zup.shared
import io.grpc.Status
import javax.inject.Singleton

@Singleton
class ChavePixExistenteExceptionHandler: ExceptionHandler<ChavePixExistenteException> {

    override fun handle(e: ChavePixExistenteException): ExceptionHandler.StatusWrapper {
        return ExceptionHandler.StatusWrapper(
                Status.ALREADY_EXISTS
                        .withDescription(e.message)
                        .withCause(e)
        )
    }

    override fun supports(e: Exception): Boolean {
        return e is ChavePixExistenteException
    }

}