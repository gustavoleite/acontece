package gustavo.acontece.inject

import android.app.Application
import dagger.Component
import gustavo.acontece.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(app: Application)
    fun inject(mainActivity: MainActivity)
}