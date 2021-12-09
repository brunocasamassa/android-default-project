package br.com.lea.easyvendas.domain.usecase.changePassword

import br.com.lea.easyvendas.data.user.UserRepository
import br.com.lea.easyvendas.domain.model.FireEmail
import br.com.lea.easyvendas.domain.model.User
import br.com.lea.easyvendas.domain.model.Welcome
import com.lea.commons.datalayer.model.CommonError

class ChangePasswordUseCaseImpl(private val repository: UserRepository):ChangePasswordUseCase {



    override suspend fun changePassword(user: String, password: String, code: String): Welcome {
        return  try {
            val user = User(
                username = user,
                password = password,
                accessCode = code
            )
            repository.changePassword(user)
        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun forgotPassword(email: FireEmail): CommonError {
        try {
            return repository.forgotPassword(email)
        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun codeReceived(code: String): String {
        TODO("Not yet implemented")
    }
}