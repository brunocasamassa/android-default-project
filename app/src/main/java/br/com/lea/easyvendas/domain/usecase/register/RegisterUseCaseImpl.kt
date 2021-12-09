package br.com.lea.easyvendas.domain.usecase.register

import br.com.lea.easyvendas.data.user.UserRepository
import br.com.lea.easyvendas.domain.model.User
import br.com.lea.easyvendas.domain.model.Welcome
import java.lang.Exception

class RegisterUseCaseImpl(private val repository: UserRepository):RegisterUseCase {

    override suspend fun register(user: String, password: String, code:String): Welcome {
      return  try {
            val user = User(
                username = user,
                password = password,
                accessCode = code
            )
            repository.register(user)
        }catch (e:Exception){
            throw e
        }
    }
}