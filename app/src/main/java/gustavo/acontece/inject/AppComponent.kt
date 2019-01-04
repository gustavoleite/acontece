package gustavo.acontece.inject

import android.app.Application
import dagger.Component
import gustavo.acontece.ui.checkin.CheckinActivity
import gustavo.acontece.ui.eventdetail.EventDetailActivity
import gustavo.acontece.ui.events.EventsActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(app: Application)
    fun inject(eventsActivity: EventsActivity)
    fun inject(eventDetailActivity: EventDetailActivity)
    fun inject(checkinActivity: CheckinActivity)
}