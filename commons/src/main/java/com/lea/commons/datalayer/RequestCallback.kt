package com.lea.commons.datalayer

interface RequestCallback<T,E> {

    fun onSuccess(response: T)

    fun onFailure(error: E)
}
