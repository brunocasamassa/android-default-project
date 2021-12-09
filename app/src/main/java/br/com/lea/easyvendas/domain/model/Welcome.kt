package br.com.lea.easyvendas.domain.model
// To parse the JSON, install Klaxon and do:
//
//   val welcome6 = Welcome6.fromJson(jsonString)


import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json


data class Welcome(
    @SerializedName(value = "content")
    val content: Content,
    @SerializedName(value = "messages")
    val messages: List<String>,
    @SerializedName(value = "statusCode")
    val statusCode: Long
)

data class Content(
    @SerializedName(value = "userAccountID")
    val userAccountID: String,
    @SerializedName(value = "tenant")
    val tenant: Tenant,
    @SerializedName(value = "username")
    val username: String,
    @SerializedName(value = "firstName")
    val firstName: String,
    @SerializedName(value = "lastName")
    val lastName: String,

    @SerializedName(value = "email")
    val email: String,
    @SerializedName(value = "status")
    val status: String,
    @SerializedName(value = "token")
    val token: String,

    //TODO campos para changePassword
    @SerializedName(value = "userStatus")
    val userStatus: String,

    @SerializedName(value = "phone")
    val phone: String,
)

data class Tenant(
    @SerializedName(value = "tenantId")
    val tenantID: String,
    @SerializedName(value = "name")
    val name: String,
    @SerializedName(value = "isRoot")
    val isRoot: Boolean
)


