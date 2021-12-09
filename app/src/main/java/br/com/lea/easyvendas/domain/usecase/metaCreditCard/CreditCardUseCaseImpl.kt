package br.com.lea.easyvendas.domain.usecase.metaCreditCard


import br.com.lea.easyvendas.data.home.HomeRepository
import br.com.lea.easyvendas.domain.model.CreditCardLeao
import br.com.lea.easyvendas.domain.model.CreditCardVC


class CreditCardUseCaseImpl(private val repository: HomeRepository): CreditCardUseCase {
    override suspend fun creditCardVC(
        metaValue: String,
        faltaMetaValue: String,
        diasUteis: Int,
        diasCorrid: Int,
        porcentIdeal: String,
        porcentGrafic: String
    ): CreditCardVC {
        var creditCardVC = CreditCardVC(
            metaValue = metaValue,
            faltaMetaValue = faltaMetaValue,
            diasUteis = diasUteis,
            diasCorrid = diasCorrid,
            porcentIdeal = porcentIdeal,
            porcentGrafic = porcentGrafic
        )
        return repository.creditCardVC(creditCardVC)
    }

    override suspend fun creditCardLeao(
        metaValue: String,
        faltaMetaValue: String,
        today: String,
        startMonth: String,
        lastMonth: String,
        porcentIdeal: String,
        porcentGrafic: String
    ): CreditCardLeao {
       var creditCardLeao = CreditCardLeao(
           metaValue = metaValue,
           faltaMetaValue = faltaMetaValue,
           today = today,
           startMonth = startMonth,
           lastMonth = lastMonth,
           porcentIdeal = porcentIdeal,
           porcentGrafic = porcentGrafic
       )
        return repository.creditCardLeao(creditCardLeao)
    }
}













