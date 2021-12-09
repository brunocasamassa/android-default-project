package br.com.lea.easyvendas.ui.utils.fragmentviews.listproducts.viewmodel.usecase

import br.com.lea.easyvendas.domain.model.cart.RequestCart

class UpdateProductUseCase(
    private val repository: br.com.carrefour.features.c4components.model.repository.ListProductsRepository
) {

    suspend operator fun invoke(sku: String, item: RequestCart): RequestCart {
        try {
            return repository.updateOnCart(sku, item)
        } catch (throwable: Throwable) {
             throw throwable
        }
    }
}
