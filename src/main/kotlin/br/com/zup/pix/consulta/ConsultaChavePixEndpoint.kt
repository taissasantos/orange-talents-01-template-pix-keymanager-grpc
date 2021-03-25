package br.com.zup.pix.consulta


import br.com.zup.CarregaChavePixRequest
import br.com.zup.CarregaChavePixResponse
import br.com.zup.CarregaChavePixServiceGrpc
import br.com.zup.pix.external.BancoCentralClient
import br.com.zup.pix.repository.ChavePixRespository
import io.micronaut.validation.validator.Validator
import br.com.zup.shared.ErrorHandler
import io.grpc.stub.StreamObserver

import javax.inject.Inject
import javax.inject.Singleton

@ErrorHandler
@Singleton
class ConsultaChavePixEndpoint(@Inject private val repository: ChavePixRespository,
                               @Inject private val bcbClient: BancoCentralClient,
                               @Inject private val validator: Validator):
        CarregaChavePixServiceGrpc.CarregaChavePixServiceImplBase() {

    override fun carrega(request: CarregaChavePixRequest,
                         responseObserver: StreamObserver<CarregaChavePixResponse>) {

        val filtro = request?.toModel(validator)
        val chaveInfo = filtro.filtra(repository, bcbClient)

        responseObserver.onNext(CarregaChavePixResponseConverter().converter(chaveInfo))
        responseObserver.onCompleted()
    }

}


