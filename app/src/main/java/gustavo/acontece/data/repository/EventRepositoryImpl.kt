package gustavo.acontece.data.repository

import gustavo.acontece.data.api.EventApi
import gustavo.acontece.data.entity.mapper.EventMapper
import gustavo.acontece.data.entity.model.Event
import gustavo.acontece.data.entity.model.EventPreview
import io.reactivex.Single
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(private val eventApi: EventApi) : EventRepository {

    override fun fetchEvents(): Single<List<EventPreview>> {
        return eventApi
            .getAllEvents()
            .map { EventMapper.toEventPreviewList(it) }
    }

    override fun fetchEventDetail(id: String): Single<Event> {
        return eventApi
            .getEvent(id)
            .map { EventMapper.toEvent(it) }
    }
}