package br.com.lea.easyvendas.domain.model.stores

import android.content.Context

data class StoreDate(
    val day: String,
    val dayWeek: String,
    val stores: Store,
){
    companion object {

        fun getDateStoresMoked(context: Context): ArrayList<StoreDate> {
            var list = arrayListOf<StoreDate>()
            for (i in 0..1) {
                list.add(
                    StoreDate(
                        day = "01",
                        dayWeek = "Segunda",
                        stores = Store(
                        name = "Mercado Vila Olimpia",
                            cnpj = "1215"
                        )
                    )
                )

                list.add(
                    StoreDate(
                        day = "02",
                        dayWeek = "Terça",
                        stores = Store(
                            name = "Mercado Vila Olimpia",
                            cnpj = "1215",

                            )
                    )
                )
            }

            list.sortBy { it.day }
            return list
        }
    }
}

