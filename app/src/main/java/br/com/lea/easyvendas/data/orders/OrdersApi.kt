package br.com.lea.easyvendas.data.orders

 import br.com.lea.easyvendas.domain.model.catalog.Catalog
 import br.com.lea.easyvendas.domain.model.orders.ProductVO
import retrofit2.http.GET
import retrofit2.http.Header
 import retrofit2.http.Path

interface OrdersApi {

    //todo pegar endpoint e objeto corretos + ver premissa da chamada

    @GET("api/catalog/{route}")
    suspend fun requestOrders(
        @Path("route") route:String,
        @Header("Content-Type") content: String = "application/json",
    ): Catalog
}