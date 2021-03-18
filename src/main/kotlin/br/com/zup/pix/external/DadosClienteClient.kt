package br.com.zup.pix.external

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client


@Client("http://localhost:9091")
interface DadosClienteClient{

    @Get("/api/v1/clientes/{clienteId}/contas")
    fun buscaDadosConta(@PathVariable clienteId: String, @QueryValue tipo: String): HttpResponse<DadosClientResponse>
}
