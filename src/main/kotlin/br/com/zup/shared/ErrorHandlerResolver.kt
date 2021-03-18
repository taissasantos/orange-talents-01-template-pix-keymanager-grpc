package br.com.zup.shared

import java.lang.IllegalStateException
import javax.inject.Singleton

@Singleton
class ErrorHandlerResolver(private val handlers: List<ExceptionHandler<Exception>>){

    fun resolve(e: Exception): ExceptionHandler<Exception>{
        val filtroHandles = handlers.filter { f-> f.supports(e) }
        if(filtroHandles.size >1)
            throw IllegalStateException("Too many handlers supporting the same exception")
        return filtroHandles.first()
    }

}

