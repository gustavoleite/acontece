package gustavo.acontece.feature.events

import android.arch.lifecycle.ViewModel
import gustavo.acontece.data.entity.domain.Ceremony

class EventsViewModel : ViewModel() {

    val eventsList: List<Ceremony> = emptyList()

    fun fetchData() {

    }
}