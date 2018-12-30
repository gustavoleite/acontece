package gustavo.acontece.data.repository

import gustavo.acontece.data.api.EventApi
import gustavo.acontece.data.entity.domain.EventPreview
import gustavo.acontece.data.entity.mapper.EventMapper
import io.reactivex.Single
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(private val eventApi: EventApi) : EventRepository {

    override fun fetchCeremonies(): Single<List<EventPreview>> {
        return eventApi
            .getAllEvents()
            .map { EventMapper.toEventPreviewList(it) }
    }
}