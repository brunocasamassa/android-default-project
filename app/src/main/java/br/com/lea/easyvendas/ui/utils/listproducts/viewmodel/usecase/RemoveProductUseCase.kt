package br.com.lea.easyvendas.ui.utils.fragmentviews.listproducts.viewmodel.usecase

import br.com.lea.easyvendas.domain.model.cart.RequestCart


class RemoveProductUseCase(
    private val repository: br.com.carrefour.features.c4components.model.repository.ListProductsRepository
) {

    suspend operator fun invoke(sku: String, discardConsignment: Boolean): RequestCart {
        try {
            return repository.removeFromCart(sku, discardConsignment)
        } catch (throwable: Throwable) {
             throw throwable
        }
    }
}
