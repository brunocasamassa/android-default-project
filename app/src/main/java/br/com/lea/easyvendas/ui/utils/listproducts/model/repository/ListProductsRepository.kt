package br.com.carrefour.features.c4components.model.repository

import br.com.lea.easyvendas.domain.model.cart.RequestCart
import br.com.lea.easyvendas.ui.utils.fragmentviews.listproducts.model.remote.ProductListAPI


class ListProductsRepository (private val productListAPI: ProductListAPI ){

    suspend fun getCart(discardConsignment: Boolean): RequestCart {
        return try {
                 productListAPI.getCartVtex(discardConsignment)

        } catch (ex: Exception) {
            RequestCart()
         }
    }

    suspend fun updateOnCart(sku: String, item: RequestCart): RequestCart {
        return productListAPI.updateOnCart(sku,item)
    }

    suspend fun removeFromCart(sku: String, discardConsignment: Boolean): RequestCart {
        return productListAPI.removeFromCartVtex(sku,discardConsignment)
    }

    suspend fun addProductToCart(item: RequestCart): RequestCart? {
        return productListAPI.addProductToCartVtex(item)
    }


}
