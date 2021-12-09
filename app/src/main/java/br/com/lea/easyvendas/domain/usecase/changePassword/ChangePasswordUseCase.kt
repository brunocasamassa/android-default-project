package br.com.lea.easyvendas.domain.usecase.changePassword

import br.com.lea.easyvendas.domain.model.FireEmail
import br.com.lea.easyvendas.domain.model.Welcome
import com.lea.commons.datalayer.model.CommonError

interface ChangePasswordUseCase {
    suspend fun changePassword(user: String, password:String, code:String): Welcome

    suspend fun forgotPassword(email:FireEmail): CommonError

    suspend fun codeReceived(code: String): String
}