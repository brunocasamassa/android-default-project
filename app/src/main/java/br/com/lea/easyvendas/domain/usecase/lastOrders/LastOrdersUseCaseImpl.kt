package br.com.lea.easyvendas.domain.usecase.lastOrders

import br.com.lea.easyvendas.data.home.HomeRepository
import br.com.lea.easyvendas.domain.model.LastOrders

class LastOrdersUseCaseImpl(
    private val repository: HomeRepository
    ) : LastOrdersUseCase {

    override suspend fun getLastOrders(): List<LastOrders> {
        return repository.getLastOrders()
    }

}