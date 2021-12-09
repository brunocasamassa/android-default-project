package br.com.lea.easyvendas.domain.usecase.login

import br.com.lea.easyvendas.domain.model.Welcome

interface LoginUserUseCase {
    suspend fun login(user: String, password:String): Welcome
}