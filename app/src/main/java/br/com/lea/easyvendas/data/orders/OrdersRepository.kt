package br.com.lea.easyvendas.data.orders

import br.com.lea.easyvendas.domain.model.catalog.Catalog
import br.com.lea.easyvendas.domain.model.orders.ProductVO

class OrdersRepository(val api: OrdersApi) {
    suspend fun getOrders(route:String): Catalog{
        return api.requestOrders(route)
    }
}