package br.com.lea.easyvendas.data.user

import br.com.lea.easyvendas.domain.model.FireEmail
import br.com.lea.easyvendas.domain.model.User
import br.com.lea.easyvendas.domain.model.Welcome
import com.lea.commons.datalayer.model.CommonError


interface UserDataSource{

    suspend fun loginUser(user: User): Welcome

    suspend fun register(user: User):Welcome

    suspend fun changePassword(user: User): Welcome

    suspend fun forgotPassword(email:FireEmail): CommonError

}