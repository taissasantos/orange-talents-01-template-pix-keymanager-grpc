package br.com.zup.pix.consulta

import br.com.zup.CarregaChavePixRequest
import io.micronaut.validation.validator.Validator
import javax.validation.ConstraintViolationException

fun CarregaChavePixRequest.toModel(validator: Validator): Filtro {

    val filtro = when(filtroCase){
        CarregaChavePixRequest.FiltroCase.PIXID -> pixId.let {
            Filtro.PorPixId(clientId = it.clientId, pixId = it.pixId)
        }
        CarregaChavePixRequest.FiltroCase.CHAVE -> Filtro.PorChave(chave)
        CarregaChavePixRequest.FiltroCase.FILTRO_NOT_SET -> Filtro.Invalido()
    }

    val validation = validator.validate(filtro)
    if(validation.isNotEmpty())
        throw ConstraintViolationException(validation)

    return filtro

}