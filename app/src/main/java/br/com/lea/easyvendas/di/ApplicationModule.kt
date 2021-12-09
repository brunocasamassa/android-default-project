package br.com.lea.easyvendas.di

import br.com.lea.apimanager.HttpClient
import br.com.lea.easyvendas.data.orders.OrdersApi
import br.com.lea.easyvendas.data.orders.OrdersRepository
import br.com.lea.easyvendas.data.user.UserApi
import br.com.lea.easyvendas.data.user.UserDataSource
import br.com.lea.easyvendas.data.user.UserDataSourceImpl
import br.com.lea.easyvendas.data.user.UserRepository
import br.com.lea.easyvendas.data.products.CatalogApi
import br.com.lea.easyvendas.data.products.CatalogRepository
import br.com.lea.easyvendas.domain.usecase.changePassword.ChangePasswordUseCase
import br.com.lea.easyvendas.domain.usecase.changePassword.ChangePasswordUseCaseImpl
import br.com.lea.easyvendas.domain.usecase.login.LoginUserUseCase
import br.com.lea.easyvendas.domain.usecase.login.LoginUserUseCaseImpl
import br.com.lea.easyvendas.domain.usecase.register.RegisterUseCase
import br.com.lea.easyvendas.domain.usecase.register.RegisterUseCaseImpl
import br.com.lea.easyvendas.ui.fragment.orders.OrdersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object ApplicationModule {
    private val modules = module {

        factory { get<HttpClient>().create(UserApi::class.java) }
        factory { get<HttpClient>().create(CatalogApi::class.java) }
        factory { get<HttpClient>().create(OrdersApi::class.java) }
        factory<UserDataSource> { UserDataSourceImpl(get()) }

        factory { UserRepository(get()) }
        factory { CatalogRepository(get()) }
        factory { OrdersRepository(get()) }

        factory<LoginUserUseCase> { LoginUserUseCaseImpl(get()) }

        factory<RegisterUseCase> { RegisterUseCaseImpl(get()) }

        factory<ChangePasswordUseCase> { ChangePasswordUseCaseImpl(get()) }

        //factory<HomeUseCase> { HomeUseCaseImpl(get()) }

        viewModel { OrdersViewModel(get()) }
    }

    fun loadModules() {
        loadKoinModules(modules)
    }


}


