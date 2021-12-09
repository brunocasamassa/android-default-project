package br.com.lea.easyvendas.di

 import br.com.lea.apimanager.HttpClient
import br.com.lea.easyvendas.ui.utils.fragmentviews.listproducts.model.remote.ProductListAPI
import br.com.lea.easyvendas.ui.utils.fragmentviews.listproducts.viewmodel.ProductListViewModel
import br.com.lea.easyvendas.ui.utils.fragmentviews.listproducts.viewmodel.usecase.AddProductsInCartUseCase
 import br.com.lea.easyvendas.ui.utils.fragmentviews.listproducts.viewmodel.usecase.GetCartUseCase
 import br.com.lea.easyvendas.ui.utils.fragmentviews.listproducts.viewmodel.usecase.RemoveProductUseCase
import br.com.lea.easyvendas.ui.utils.fragmentviews.listproducts.viewmodel.usecase.UpdateProductUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

/*** Módulo para inserirmos os componentes visuais que podem ser genéricos o suficiente para o app inteiro instanciar daqui*/

object LiveComponentsModule {
    private val listComponentsModule = module {
        viewModel {
            ProductListViewModel(
                get(),
                get(),
                get(),
                get()
            )
        }
        factory {
            br.com.carrefour.features.c4components.model.repository.ListProductsRepository(
                get<HttpClient>().create(ProductListAPI::class.java)
            )
        }

        factory {
            AddProductsInCartUseCase(
                get()
            )
        }
        factory {
            RemoveProductUseCase(
                get()
            )
        }
        factory {
            UpdateProductUseCase(
                get()
            )
        }
        factory {
            GetCartUseCase(
                get()
            )
        }
    }


    fun loadComponentsModule() {
        loadKoinModules(listOf(listComponentsModule))
    }
}
