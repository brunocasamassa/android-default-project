package br.com.lea.easyvendas.domain.usecase.login

import br.com.lea.easyvendas.data.user.UserRepository
import br.com.lea.easyvendas.domain.model.User
import br.com.lea.easyvendas.domain.model.Welcome
import java.lang.Exception

class LoginUserUseCaseImpl(
    private val repository: UserRepository
): LoginUserUseCase {
    override suspend fun login(user: String, password: String): Welcome {
        return try {
            val user = User(
                username = user,
                password = password
            )
            repository.loginUser(user)
        }catch (e: Exception){
            throw e
        }
    }

}