package br.com.lea.apimanager.di

import br.com.lea.apimanager.HttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object ApiManagerModule {
    private val modules = module {
        factory { HttpClient(androidApplication()) }
    }

    fun loadModules() {
        loadKoinModules(modules)
    }

    lateinit var baseUrl: String
    lateinit var appType: String
    fun build(baseUrl: String, appType:String) {
        ApiManagerModule.baseUrl = baseUrl
        ApiManagerModule.appType = appType
    }

    const val API_KEY = "0CA486C5-0967-4A28-8147-2351E42E9A0A"

}
