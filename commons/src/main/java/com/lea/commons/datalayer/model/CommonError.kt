package com.lea.commons.datalayer.model

import com.google.gson.annotations.SerializedName

class CommonError {
    var parserVersion: String? = null
    var application: String? = null
    var messageKey: String? = null

    var title: String? = null //deprecated

    var status: Int = 0
    var error: String? = null
    var exception: String? = null

    @SerializedName("content")
    var content: Boolean = true
    @SerializedName("statusCode")
    var statusCode: Int = 0
    @SerializedName("messages")
    var messages: List<String>? = null

    constructor(message: List<String>?) {
        this.messages = message
    }

    constructor(messages: List<String>?, statusCode: Int, content:Boolean) {
        this.messages = messages
        this.statusCode = statusCode
        this.content = content

    }

    override fun toString(): String {
        return "(message=$messages, code=$statusCode)"
    }

    companion object {
        const val UNKNOWN = -100
        const val UPDATE = -1
        const val NO_INTERNET = -2
        const val DER_FAILED = -3
        const val DUPLICATE_DER = -4
        const val TEMPORARY_AFTER_PERMANENT = -5
        const val TIMEOUT = 504
        const val AUTH_ERROR = 403
        const val NOT_FOUND = 404
        const val PENDING_DOC = 1917
        const val NO_CONTENT = 204

        fun internetError(): CommonError {
            return CommonError(
                listOf("No internet error"),
                NO_INTERNET,
                true
            )
        }

        fun notFoundError(): CommonError {
            return CommonError(
                listOf("error not found"),
                NOT_FOUND,
                true
            )
        }
    }
}
