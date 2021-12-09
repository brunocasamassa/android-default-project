package br.com.lea.easyvendas.domain.usecase.metaCreditCard

import br.com.lea.easyvendas.domain.model.CreditCardLeao
import br.com.lea.easyvendas.domain.model.CreditCardVC

interface CreditCardUseCase {

    suspend fun creditCardVC(
        metaValue: String,
        faltaMetaValue: String,
        diasUteis: Int,
        diasCorrid: Int,
        porcentIdeal: String,
        porcentGrafic: String
    ): CreditCardVC


    suspend fun creditCardLeao(
         metaValue :String,
         faltaMetaValue : String,
         today:String,
         startMonth: String,
         lastMonth: String,
         porcentIdeal: String,
         porcentGrafic: String
    ): CreditCardLeao
}