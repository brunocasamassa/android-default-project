package br.com.lea.easyvendas.domain.usecase.actionPromo

import br.com.lea.easyvendas.domain.model.ActionPromo

interface ActionPromoUseCase {

    suspend fun getActionsPromo(): List<ActionPromo>

}