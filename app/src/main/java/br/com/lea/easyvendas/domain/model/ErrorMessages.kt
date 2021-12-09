package br.com.lea.easyvendas.domain.model

import com.google.gson.annotations.SerializedName

data class ErrorMessages(
    @SerializedName(value = "messages")
    val messages: List<String>,
    @SerializedName(value = "userStatus")
    val statusCode: Int,
    @SerializedName(value = "statusCode")
    val userStatus: String
)