package br.com.lea.apimanager.session.interceptors

import br.com.lea.apimanager.di.ApiManagerModule
import com.lea.commons.constants.ProjectConstants
import com.lea.commons.datalayer.shared.SharedPreferenceHandler
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.component.KoinComponent
import java.io.IOException

class AuthInterceptor : Interceptor, KoinComponent {


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        return with(chain.request().newBuilder()) {

            val token: String = SharedPreferenceHandler.getStringForced(ProjectConstants.TOKEN)

            if (!token.isNullOrEmpty()) {
                removeHeader("Authorization")
                addHeader("Authorization", "Bearer $token")
            }

            removeHeader("x-api-Key")
            addHeader("x-api-Key", ApiManagerModule.API_KEY)

            chain.proceed(build())
        }
    }
}
