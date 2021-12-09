package br.com.lea.easyvendas.domain.usecase.register

import br.com.lea.easyvendas.domain.model.Welcome

interface RegisterUseCase {
    suspend fun register(user: String, password:String, code:String): Welcome
}