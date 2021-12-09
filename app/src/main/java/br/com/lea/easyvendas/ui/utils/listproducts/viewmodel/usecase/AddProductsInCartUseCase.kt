package br.com.lea.easyvendas.ui.utils.fragmentviews.listproducts.viewmodel.usecase


import br.com.lea.easyvendas.domain.model.cart.RequestCart

class AddProductsInCartUseCase(private val repository: br.com.carrefour.features.c4components.model.repository.ListProductsRepository) {

    suspend operator fun invoke(item: RequestCart): RequestCart? {
        return repository.addProductToCart(item)
    }
}
