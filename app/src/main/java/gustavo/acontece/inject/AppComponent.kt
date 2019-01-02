package gustavo.acontece.inject

import android.app.Application
import dagger.Component
import gustavo.acontece.ui.eventdetail.EventDetailActivity
import gustavo.acontece.ui.home.HomeActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(app: Application)
    fun inject(homeActivity: HomeActivity)
    fun inject(eventDetailActivity: EventDetailActivity)
}