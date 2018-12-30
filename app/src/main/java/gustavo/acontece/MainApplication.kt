package gustavo.acontece

import android.app.Application
import gustavo.acontece.inject.AppComponent
import gustavo.acontece.inject.AppModule
import gustavo.acontece.inject.DaggerAppComponent

class MainApplication : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    init {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}