package br.com.lea.easyvendas.domain.model.orders

import br.com.lea.easyvendas.domain.model.catalog.Catalog
import java.io.Serializable


data class ProductVO(
    val name: String,
    var sku: String,
    var quantity: Int = 0,
    var onCart: Boolean = false,
    var loading: Boolean = false,
    var price: String?,
    val thumbnail: String,
    val stockLevel: Int
) : Serializable {


    companion object {
        fun mockList(): List<ProductVO> {

            val imageUrl =
                "https://cdn.entrypoint.directory/assets/39790/produtos/955/whey-protein-35-unilife-900g-sabor-morango.jpg"
            return listOf(
                ProductVO(
                    "mockedProduct0",
                    "0001",
                    3,
                    price = "33,90",
                    thumbnail = imageUrl,
                    stockLevel = 20
                ),
                ProductVO(
                    "mockedProduct1",
                    "0002",
                    3,
                    price = "33,90",
                    thumbnail = imageUrl,
                    stockLevel = 20
                ),
                ProductVO(
                    "mockedProduct2",
                    "0003",
                    3,
                    price = "33,90",
                    thumbnail = imageUrl,
                    stockLevel = 20
                ),
                ProductVO(
                    "mockedProduct3",
                    "0004",
                    3,
                    price = "33,90",
                    thumbnail = imageUrl,
                    stockLevel = 20
                ),
                ProductVO(
                    "mockedProduct4",
                    "0005",
                    3,
                    price = "33,90",
                    thumbnail = imageUrl,
                    stockLevel = 20
                ),
                ProductVO(
                    "mockedProduct5",
                    "0006",
                    3,
                    price = "33,90",
                    thumbnail = imageUrl,
                    stockLevel = 20
                ),
                ProductVO(
                    "mockedProduct6",
                    "00017",
                    3,
                    price = "33,90",
                    thumbnail = imageUrl,
                    stockLevel = 20
                )
            )
        }

        fun createListFromCatalog(catalog: Catalog): List<ProductVO> {
            var arrayOfProducts = ArrayList<ProductVO>()

            catalog.catalogContent.forEach {
                var productVO = ProductVO(
                    name = it.name,
                    sku = it.code,
                    quantity = 0,
                    price = "R$0,00",
                    thumbnail = it.images.firstOrNull()?.url ?: "",
                    stockLevel = 10
                )
                arrayOfProducts.add(productVO)

            }

            return arrayOfProducts

        }
    }

}
