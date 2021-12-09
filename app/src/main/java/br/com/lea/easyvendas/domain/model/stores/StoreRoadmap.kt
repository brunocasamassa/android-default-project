package br.com.lea.easyvendas.domain.model.stores

import android.content.Context
import br.com.lea.easyvendas.R
import java.util.*

data class StoreRoadmap(
    var store: Store,
    var date: String
) {

    companion object {

        fun getStoresRoadmapMocked(context: Context): ArrayList<StoreRoadmap> {
            var arrayRoadMap = ArrayList<StoreRoadmap>()
            for (i in 0..9) {
                arrayRoadMap.add(retrieveStoreMockedByQuantity(context,i))
                arrayRoadMap.add(retrieveStoreMockedByQuantity(context,i))
                arrayRoadMap.add(retrieveStoreMockedByQuantity(context,i))
                arrayRoadMap.add(retrieveStoreMockedByQuantity(context,i))
                arrayRoadMap.add(retrieveStoreMockedByQuantity(context,i))
            }

            return arrayRoadMap
        }

        private fun retrieveStoreMockedByQuantity(context: Context, i: Int): StoreRoadmap {
            return     StoreRoadmap(
                Store(
                    cnpj = "000000$i",
                    name = "Mocked Store n$i",
                    code = "010101$i$i$i",
                    rede = "Rede 1",
                    isActive = Random().nextBoolean(),
                    uf = context.resources.getStringArray(
                        R.array.list_uf
                    )[i],
                    city = context.resources.getStringArray(
                        R.array.list_city
                    )[i],
                    neighbors = context.resources.getStringArray(
                        R.array.list_neighboors
                    )[i],
                    channel = context.resources.getStringArray(
                        R.array.list_channel
                    )[i],
                    category = if (i == 0 || i == 2 || i == 5) CategoryStoreEnum.SEM_VENDAS else if (i == 1 || i == 9) CategoryStoreEnum.SEM_VISITA else CategoryStoreEnum.LOJAS_OURO,
                    price = ((i + 1) * 400)

                ), "${i+1}/11/2021"
            )

        }
    }
}

