package br.com.zup.pix.remove

import br.com.zup.RemoveChavePixPixResponse
import br.com.zup.RemoveChavePixRequest
import br.com.zup.RemoveChavePixServiceGrpc
import br.com.zup.shared.ErrorHandler
import io.grpc.stub.StreamObserver
import javax.inject.Inject
import javax.inject.Singleton

@ErrorHandler
@Singleton
class RemoveChaveEndpoint(@Inject private val service: RemoveChavePixService) :
        RemoveChavePixServiceGrpc.RemoveChavePixServiceImplBase() {

    override fun remove(request: RemoveChavePixRequest,
                        responseObserver: StreamObserver<RemoveChavePixPixResponse>) {

       service.remove(clienteId = request.clientId, pixId = request.pixId)

        responseObserver.onNext(RemoveChavePixPixResponse.newBuilder()
                .setClientId(request.clientId)
                .setPixId(request.pixId)
                .build())
        responseObserver.onCompleted()

    }


}