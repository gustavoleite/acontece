package gustavo.acontece.data.repository

import gustavo.acontece.data.entity.model.Event
import gustavo.acontece.data.entity.model.EventPreview
import io.reactivex.Single

interface EventRepository {
    fun fetchEvents(): Single<List<EventPreview>>
    fun fetchEventDetail(id: String): Single<Event>
}