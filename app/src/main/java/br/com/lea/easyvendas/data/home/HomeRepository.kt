package br.com.lea.easyvendas.data.home

import br.com.lea.easyvendas.domain.model.*

class HomeRepository(private val dataSource: HomeDataSource) {

    suspend fun creditCardVC(creditCardVC: CreditCardVC): CreditCardVC{
        return dataSource.creditCardVC(creditCardVC)
    }

    suspend fun creditCardLeao(creditCardLeao: CreditCardLeao): CreditCardLeao{
        return dataSource.creditCardLeao(creditCardLeao)
    }

    suspend fun getLastOrders() :List<LastOrders>{
        return dataSource.getLastOrders()
    }


    suspend fun getScriptsDay(): List<ScriptsDay>{
        return dataSource.getScriptDay()
    }

    suspend fun getActionsPromo(): List<ActionPromo>{
        return dataSource.getActionsPromo()
    }
}