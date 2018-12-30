package gustavo.acontece.feature.events

import android.arch.lifecycle.ViewModel
import gustavo.acontece.data.entity.domain.EventPreview
import javax.inject.Inject

class EventsViewModel @Inject constructor(): ViewModel() {

    val eventsList: List<EventPreview> = emptyList()

    fun fetchData() {

    }
}