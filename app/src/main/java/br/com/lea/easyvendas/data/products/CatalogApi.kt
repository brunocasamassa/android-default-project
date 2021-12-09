package br.com.lea.easyvendas.data.products


import br.com.lea.easyvendas.domain.model.catalog.Catalog
import retrofit2.http.*

interface CatalogApi {

    @GET("api/catalog/{route}")
    suspend fun getPromotions(
        @Path("route") route:String,
        @Header("Content-Type") content: String = "application/json",
    ): Catalog

}