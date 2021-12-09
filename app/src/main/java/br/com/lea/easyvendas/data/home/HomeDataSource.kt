package br.com.lea.easyvendas.data.home

import br.com.lea.easyvendas.domain.model.*

interface HomeDataSource {

    suspend fun getLastOrders() :List<LastOrders>

    suspend fun creditCardVC(creditCardVC: CreditCardVC): CreditCardVC

    suspend fun creditCardLeao(creditCardLeao: CreditCardLeao): CreditCardLeao

    suspend fun getScriptDay(): List<ScriptsDay>

    suspend fun getActionsPromo(): List<ActionPromo>
}