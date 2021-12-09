package br.com.lea.easyvendas.data.user

import br.com.lea.easyvendas.domain.model.*
import com.lea.commons.datalayer.model.CommonError
import retrofit2.http.*

interface UserApi {

    @POST("api/auth/signin")
    suspend fun loginUser(
        @Header("Content-Type") content:String,
        @Header("x-api-Key") key :String,
        @Body request: UserRequest
    ): Welcome



    @POST("api/auth/firstAccess")
    suspend fun firstAccess(
        @Header("Content-Type") content:String,
        @Header("x-api-Key") key :String,
        @Body request: User
    ): Welcome


    @POST("api/auth/forgotPassword")
    suspend fun forgotPassword(
        @Header("Content-Type") content:String,
        @Header("x-api-Key") key :String,
        @Body request: FireEmail
    ): CommonError


    @POST("api/auth/changePassword")
    suspend fun changePassword(
        @Header("Content-Type") content:String,
        @Header("x-api-Key") key :String,
        @Body request: User
    ): Welcome

/*    @GET("api/")
    suspend fun loginUser(): Call<Welcome>*/


}