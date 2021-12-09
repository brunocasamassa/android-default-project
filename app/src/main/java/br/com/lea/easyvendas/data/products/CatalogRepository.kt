package br.com.lea.easyvendas.data.products

import br.com.lea.easyvendas.domain.model.catalog.Catalog

class CatalogRepository(private val api: CatalogApi) {

    suspend fun requestPromotions(route:String): Catalog {
        return api.getPromotions(route)
    }
}