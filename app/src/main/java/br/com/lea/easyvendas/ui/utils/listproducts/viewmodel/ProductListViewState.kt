package br.com.lea.easyvendas.ui.utils.fragmentviews.listproducts.viewmodel

import br.com.lea.easyvendas.domain.model.cart.RequestCart
import br.com.lea.easyvendas.domain.model.orders.ProductVO


interface ProductListViewState {
    class FinishAddProductToCart(val value: RequestCart?, val item: RequestCart) :
        ProductListViewState

    class OnCartRequested(val value: RequestCart?) :
        ProductListViewState

    class ShowGenericErrorDialog(val message: String? ,val productVO:ProductVO) :
        ProductListViewState

    class SuccessOnEditProduct(val value: RequestCart, val item: ProductVO, sku: String) :
        ProductListViewState

    class FailOnEditProduct(val message: String?, val productVO: ProductVO) :
        ProductListViewState

    class SuccessOnRemoveProduct(val value: RequestCart, val productId: String) :
        ProductListViewState
    class ShowErrorMessage(val message: String?) :
        ProductListViewState
}
