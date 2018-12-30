package gustavo.acontece.feature.events

import android.arch.lifecycle.ViewModel
import gustavo.acontece.data.entity.domain.EventPreview

class EventsViewModel : ViewModel() {

    val eventsList: List<EventPreview> = emptyList()

    fun fetchData() {

    }
}