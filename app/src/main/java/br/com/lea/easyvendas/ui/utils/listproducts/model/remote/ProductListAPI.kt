package br.com.lea.easyvendas.ui.utils.fragmentviews.listproducts.model.remote

import br.com.lea.easyvendas.domain.model.cart.RequestCart
import retrofit2.http.*

interface ProductListAPI {

    @GET("vtex/v1/cart")
    suspend fun getCartVtex(
        @Query("discardConsignment") discardConsignment: Boolean
    ): RequestCart

    @POST("vtex/v1/cart/product")
    suspend fun addProductToCartVtex(@Body item: RequestCart): RequestCart

    @PUT("vtex/v1/cart/product/{sku}")
    suspend fun updateOnCart(
        @Path("sku") sku: String,
        @Body item: RequestCart
    ): RequestCart

    @DELETE("vtex/v1/cart/product/{sku}")
    suspend fun removeFromCartVtex(
        @Path("sku") sku: String,
        @Query("discardConsignment") discardConsignment: Boolean
    ): RequestCart
}
