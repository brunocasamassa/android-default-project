package br.com.lea.easyvendas.domain.usecase.scriptDay

import br.com.lea.easyvendas.domain.model.ScriptsDay

interface ScriptDayUseCase {

    suspend fun getScriptsDay(): List<ScriptsDay>

}