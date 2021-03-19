package br.com.zup.pix.repository

import br.com.zup.pix.registra.ChavePix
import io.micronaut.context.annotation.Executable
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface ChavePixRespository : JpaRepository<ChavePix, UUID> {

    @Executable
    fun existsByChave(chave:String) : Boolean
    fun findByIdAndClientId(pixId: UUID?, clienteId: UUID?): Optional<ChavePix>

}
