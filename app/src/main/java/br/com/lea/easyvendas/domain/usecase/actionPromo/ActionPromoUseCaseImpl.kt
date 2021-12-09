package br.com.lea.easyvendas.domain.usecase.actionPromo

import br.com.lea.easyvendas.data.home.HomeRepository
import br.com.lea.easyvendas.domain.model.ActionPromo

class ActionPromoUseCaseImpl(private val repository: HomeRepository): ActionPromoUseCase {

    override suspend fun getActionsPromo(): List<ActionPromo> {
       return repository.getActionsPromo()
    }

}