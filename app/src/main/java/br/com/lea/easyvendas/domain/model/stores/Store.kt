package br.com.lea.easyvendas.domain.model.stores

import android.content.Context
import br.com.lea.easyvendas.R
import java.util.*

data class Store(
    var cnpj: String = "",
    var name: String = "",
    var code: String = "",
    var rede: String = "",
    var isActive: Boolean = false,
    var uf: String = "",
    var city: String = "",
    var neighbors: String = "",
    var channel: String = "",
    var category: CategoryStoreEnum = CategoryStoreEnum.LOJAS_OURO,
    var price: Int = 0,
    var isSelected: Boolean = false,
    var isVisible: Boolean = false,
) {

    companion object {

        fun getStoresMocked(context: Context): ArrayList<Store> {
            var array = ArrayList<Store>()
            for (i in 0..9) {
                array.add(
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

                    )
                )
            }

            return array
        }
    }
}

enum class CategoryStoreEnum {
    LOJAS_OURO, SEM_VENDAS, SEM_VISITA, ATIVO, INATIVO
}
