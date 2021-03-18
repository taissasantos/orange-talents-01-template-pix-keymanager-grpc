package br.com.zup.pix.external

import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client
import java.net.http.HttpResponse

@Client("http://localhost:9091/api/v1/private/contas/todas")
interface DadosClienteClient{

    @Get("/api/v1/clientes/{clienteId}/contas")
    fun buscaDadosConta(@PathVariable clienteId: String, @QueryValue tipo: String): HttpResponse<DadosClientResponse>
}
