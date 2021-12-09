package br.com.lea.easyvendas

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import br.com.lea.apimanager.di.ApiManagerModule
import br.com.lea.easyvendas.di.ApplicationModule
import br.com.lea.easyvendas.di.LiveComponentsModule
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.lea.commons.constants.ProjectConstants
import com.lea.commons.datalayer.shared.SharedPreferenceHandler
import com.orhanobut.hawk.Hawk
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {

    init {
        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            ApiManagerModule.loadModules()
            ApplicationModule.loadModules()
            LiveComponentsModule.loadComponentsModule()
        }
    }

    override fun onCreate() {
        super.onCreate()

        Hawk.init(this@MainApplication).build();
        ApiManagerModule.build(BuildConfig.BASE_URL, BuildConfig.APP_TYPE)

        SharedPreferenceHandler.newInstance(applicationContext)




    }

}