package br.com.zup.pix.registra

import br.com.zup.PixServiceGrpc
import br.com.zup.RegistraChavePixRequest
import br.com.zup.RegistraChavePixResponse
import io.grpc.stub.StreamObserver
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RegistraChaveEndpoint(@Inject private val service: NovaChavePixService,) :
                            PixServiceGrpc.PixServiceImplBase() {

    override fun registrar(request: RegistraChavePixRequest?, responseObserver: StreamObserver<RegistraChavePixResponse>?) {
        val novaChave = request?.toModel()
        val chaveCriada = service.registra(novaChave!!)

        responseObserver!!.onNext(RegistraChavePixResponse.newBuilder()
                .setClientId(chaveCriada.clientId.toString())
                .setPixId(chaveCriada.id.toString())
                .build())
        responseObserver.onCompleted()

    }
}