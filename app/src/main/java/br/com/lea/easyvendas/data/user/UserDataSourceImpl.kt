package br.com.lea.easyvendas.data.user


import br.com.lea.apimanager.di.ApiManagerModule.API_KEY
import br.com.lea.easyvendas.domain.model.*
import br.com.lea.easyvendas.ui.utils.Constants.Companion.CONTENT_TYPE
import com.lea.commons.datalayer.model.CommonError

class UserDataSourceImpl(val api: UserApi): UserDataSource {

    override suspend fun loginUser(user: User):  Welcome {
        return api.loginUser(
            CONTENT_TYPE,API_KEY,
            UserRequest(user.username,user.password)
        )
    }

    override suspend fun register(user: User):Welcome{
        return api.firstAccess(CONTENT_TYPE,API_KEY,user)
    }

    override suspend fun changePassword(user: User): Welcome {
       return api.changePassword(CONTENT_TYPE, API_KEY,user)
    }

    override suspend fun forgotPassword(email: FireEmail): CommonError {

        return api.forgotPassword(CONTENT_TYPE, API_KEY,email)
    }

}