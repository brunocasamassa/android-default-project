package br.com.lea.easyvendas.data.user

import br.com.lea.easyvendas.domain.model.FireEmail
import br.com.lea.easyvendas.domain.model.User
import br.com.lea.easyvendas.domain.model.Welcome
import com.lea.commons.datalayer.model.CommonError

class UserRepository(private val dataSource : UserDataSource){

    suspend fun loginUser(user: User): Welcome {
        //todo chamada para api
        return dataSource.loginUser(user)
    }

        suspend fun register(user: User): Welcome {
        //TODO chamada para api
        return dataSource.register(user)
    }

    suspend fun changePassword(user: User): Welcome {
        //TODO chamada para api
        return dataSource.changePassword(user)
    }

    suspend fun forgotPassword(email: FireEmail) : CommonError{
        return dataSource.forgotPassword(email)
    }


}