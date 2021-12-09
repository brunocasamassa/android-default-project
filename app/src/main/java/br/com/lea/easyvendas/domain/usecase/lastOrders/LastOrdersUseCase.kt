package br.com.lea.easyvendas.domain.usecase.lastOrders

import br.com.lea.easyvendas.domain.model.LastOrders

interface LastOrdersUseCase {
    suspend fun getLastOrders(): List<LastOrders>
}