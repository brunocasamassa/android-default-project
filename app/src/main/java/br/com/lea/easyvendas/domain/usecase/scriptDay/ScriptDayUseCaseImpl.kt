package br.com.lea.easyvendas.domain.usecase.scriptDay

import br.com.lea.easyvendas.data.home.HomeRepository

import br.com.lea.easyvendas.domain.model.ScriptsDay

class ScriptDayUseCaseImpl(
    private val repository: HomeRepository
) :ScriptDayUseCase {
    override suspend fun getScriptsDay(): List<ScriptsDay> {

       return repository.getScriptsDay()

    }

}